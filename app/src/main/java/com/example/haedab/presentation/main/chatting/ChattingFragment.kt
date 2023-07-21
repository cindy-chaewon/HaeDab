package com.example.haedab.presentation.main.chatting

import android.content.Context
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
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.database.RoomEntity
import com.example.haedab.databinding.FragmentChattingBinding
import com.example.haedab.databinding.FragmentOnboardingFirstBinding
import com.example.haedab.presentation.main.MainActivity
import com.example.haedab.presentation.main.category.CategoryFragment
import com.example.haedab.presentation.main.setting.SettingFragment
import com.example.haedab.presentation.onboarding.OnboardingSecondFragment
import com.example.haedab.viewmodel.ChatViewModel
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChattingFragment: BaseFragment<FragmentChattingBinding>(FragmentChattingBinding::bind, R.layout.fragment_chatting)  {

    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null

    @Inject
    lateinit var chatRVAdapter: ChatRVAdapter

    private val messageList: MutableList<RoomEntity> = mutableListOf()
    private var lastMessage = ""

    private val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {


            fillMessageList()

            createRecyclerView()

            chattingBtn.setOnClickListener {
                messageSending()
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


}