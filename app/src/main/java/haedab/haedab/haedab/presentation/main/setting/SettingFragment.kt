package haedab.haedab.haedab.presentation.main.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.common.SETTING
import haedab.haedab.haedab.databinding.FragmentSettingBinding
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity
import haedab.haedab.haedab.presentation.main.chatting.ChattingFragment

@AndroidEntryPoint
class SettingFragment: BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting) {

    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //뒤로가기
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, ChattingFragment()).commit()
            }
        })

        binding.setting1.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, LanguageFragment()).addToBackStack(null).commit()
        }

        binding.setting2.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, UseFirstFragment()).addToBackStack(null).commit()
        }

        binding.setting3.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, TermsFragment()).addToBackStack(null).commit()
        }

        binding.backBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, ChattingFragment()).commit()
        }

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it){} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }
}