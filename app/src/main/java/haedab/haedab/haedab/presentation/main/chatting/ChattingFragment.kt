package haedab.haedab.haedab.presentation.main.chatting

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.common.LoadingDialog
import haedab.haedab.haedab.database.RoomEntity
import haedab.haedab.haedab.databinding.FragmentChattingBinding
import haedab.haedab.haedab.presentation.main.setting.SettingFragment
import haedab.haedab.haedab.viewmodel.ChatViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import haedab.haedab.haedab.R

@AndroidEntryPoint
class ChattingFragment: BaseFragment<FragmentChattingBinding>(FragmentChattingBinding::bind, R.layout.fragment_chatting)  {

    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null
    private var mInterstitialAd: InterstitialAd? = null
    private val PREFS_NAME = "MyPrefsFile"

    //private var isFirstButtonClick : Boolean = false

    @Inject
    lateinit var chatRVAdapter: ChatRVAdapter

    private val messageList: MutableList<RoomEntity> = mutableListOf()
    private var lastMessage = ""

    private val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // 로딩 다이얼로그
        val dialog = LoadingDialog(requireContext())
        //isFirstButtonClick = true

        binding.apply {
            fillMessageList()

            createRecyclerView()

            chattingBtn.setOnClickListener {
                // 채팅 전송 버튼 첫번째로 눌렀을때 애드몹 전면 광고 나오는 거
                if(sharedPreferences.getBoolean("first", false)){
                    //isFirstButtonClick = false
                    setupInterstitialAd()
                    admob()
                    messageSending()

                }
                else{
                    messageSending()
                }
            }

        }

        //뒤로가기 버튼 눌렀을때
        binding.backBtn.setOnClickListener {
            chattingActivity!!.admob()
        }

        binding.settingBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, SettingFragment()).addToBackStack(null).commit()
        }

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it){} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        with(binding) {
            // 키패드
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                root.setOnApplyWindowInsetsListener { _, windowInsets ->
                    val imeHeight = windowInsets.getInsets(WindowInsets.Type.ime()).bottom
                    binding.root.setPadding(0, 0, 0, imeHeight)
                    val insets =
                        windowInsets.getInsets(WindowInsets.Type.ime() or WindowInsets.Type.systemGestures())
                    insets
                    windowInsets
                }
            } else {
                requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            }

        }


        with(binding) {
            // 입력창 클릭
            chattingEt.setOnEditorActionListener{ textView, action, event ->
                var handled = false
                if (action == EditorInfo.IME_ACTION_DONE) {
                    // 키보드 내리기
                    //val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    //imm.hideSoftInputFromWindow(chattingEt.windowToken, 0)
                    //handled = true
                }
                handled
            }
        }

    }

    private fun messageSending() {
        binding.apply {
            val message = chattingEt.text.toString().trim()
            lastMessage = message
            if (message.isNotEmpty()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getResponse(message)
                }
                chattingEt.text?.clear()
                scrollToBottom()
                //recyclerMessages.smoothScrollToPosition(messageList.size - 1)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please enter your query..",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun scrollToBottom() {
        binding.apply {
            recyclerMessages.addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
                if (bottom < oldBottom) {
                    recyclerMessages.smoothScrollToPosition(messageList.size)
                }
            }
        }
    }

    private fun createRecyclerView() {
        binding.apply {
            //getAllData()
            chatRVAdapter = ChatRVAdapter()
            val layoutManager = LinearLayoutManager(context)
            recyclerMessages.layoutManager = layoutManager
            recyclerMessages.adapter = chatRVAdapter
            recyclerMessages.scrollToPosition(messageList.size - 1)
            scrollToBottom()
        }
        //채팅 복사 기능
        chatRVAdapter.setOnItemClickListener(object :ChatRVAdapter.OnItemClickListener{
            override fun onLongClick(v: View, position: Int, text: String) {
                createClipData(text)
            }
        })
    }

    private fun getAllData() {
        viewModel.viewModelScope.launch {
            viewModel.allMessageList.collect {
                messageList.clear()
                messageList.addAll(it)
                chatRVAdapter.notifyDataSetChanged()
                Log.d("messagelist", "${messageList.size}")
            }
        }
        scrollToBottom()
    }


    private fun fillMessageList() {
        viewModel.viewModelScope.launch {
            viewModel.allMessageList.collect { messages ->
                chatRVAdapter.messageList = messages.toMutableList() as ArrayList<RoomEntity>
                if (messages.isNotEmpty()) {
                    chatRVAdapter.notifyItemInserted(messages.size - 1)
                    scrollToBottom()
                    getAllData()
                    binding.recyclerMessages.scrollToPosition(messages.size - 1)
                }
            }
        }
    }

    private fun sendLastMessage() {
        //getAllData()

                if (lastMessage == "") {
                    Toast.makeText(
                        requireContext(),
                        "You did not send any message..",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.viewModelScope.launch {
                        viewModel.getResponse(lastMessage)
                    }
                }
            }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }

    // 계좌 복사
    private fun createClipData(copy : String){
        val clipboardManager: ClipboardManager =
            requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData: ClipData = ClipData.newPlainText("copy", copy)
        //클립보드에 배치
        clipboardManager.setPrimaryClip(clipData)
    }


    private fun setupInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        val randomAdId = getRandomAdId()
        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        InterstitialAd.load(requireContext(),
            randomAdId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("DEBUG: ", adError.message.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("DEBUG: ", "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    if(sharedPreferences.getBoolean("first", false)){
                        //isFirstButtonClick = false
                        admob()
                        sharedPreferences.edit().putBoolean("first", false).apply()

                    }
                }
            })
    }

    // 랜덤으로 애드몹 광고 id 출력 함수+
    private fun getRandomAdId(): String {
        val adIds = listOf(
            "ca-app-pub-4004046235562178/3211382774",
            "ca-app-pub-4004046235562178/1878119804",
            "ca-app-pub-4004046235562178/2289221337"
        )

        val random = Random()
        val randomIndex = random.nextInt(adIds.size)
        return adIds[randomIndex]
    }


    fun admob(){
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(requireActivity())
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    Log.d("DEBUG: ", "Ad was clicked.")
                }

                override fun onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    Log.d("DEBUG: ", "Ad dismissed fullscreen content.")
                    mInterstitialAd = null
                    setupInterstitialAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e("DEBUG: ", "Ad failed to show fullscreen content.")
                    mInterstitialAd = null
                }

                override fun onAdImpression() {
                    Log.d("DEBUG: ", "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("DEBUG: ", "Ad showed fullscreen content.")
                }
            }
        } else {

        }
    }

    /*override fun onDestroy() {
        isFirstButtonClick = false
        super.onDestroy()
    }

    override fun onStop() {
        isFirstButtonClick = false
        super.onStop()
    }*/


}