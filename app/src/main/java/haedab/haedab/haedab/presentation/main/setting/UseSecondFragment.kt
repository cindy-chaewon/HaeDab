package haedab.haedab.haedab.presentation.main.setting

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.databinding.FragmentUseSecondBinding
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity
import java.util.*

@AndroidEntryPoint
class UseSecondFragment: BaseFragment<FragmentUseSecondBinding>(FragmentUseSecondBinding::bind, R.layout.fragment_use_second) {
    private val PREFS_NAME = "MyPrefsFile"
    private var configuration: Configuration = Configuration()
    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, SettingFragment()).commit()
            }
        })

        binding.nextBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, SettingFragment()).commit()
            //chattingActivity!!.back()
        }

        binding.backBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, SettingFragment()).commit()
            //parentFragmentManager.beginTransaction().remove(UseSecondFragment()).commit()
            //chattingActivity!!.back()
        }

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it){} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val selectedLanguage = sharedPreferences?.getString("lan", Locale.getDefault().language)

        when(selectedLanguage){
            "ko" -> {val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.info2_img)
                binding.infoImg.setImageDrawable(drawable)}
            "en" -> {
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.english_intro_img2)
                binding.infoImg.setImageDrawable(drawable)
            }
            "hi" -> {
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.india_intro_img2)
                binding.infoImg.setImageDrawable(drawable)
            }
            "ja" -> {
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.english_intro_img2)
                binding.infoImg.setImageDrawable(drawable)
            }
            "de" -> {
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.english_intro_img2)
                binding.infoImg.setImageDrawable(drawable)
            }
            "fr" -> {
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.english_intro_img2)
                binding.infoImg.setImageDrawable(drawable)
            }
            else -> {
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.info2_img)
                binding.infoImg.setImageDrawable(drawable)
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }
}