package haedab.haedab.haedab.presentation.main.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.databinding.FragmentUseFirstBinding
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity

@AndroidEntryPoint
class UseFirstFragment: BaseFragment<FragmentUseFirstBinding>(FragmentUseFirstBinding::bind, R.layout.fragment_use_first) {


    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, UseSecondFragment()).commit()
        }

        binding.backBtn.setOnClickListener {
            //chattingActivity!!.back()
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, SettingFragment()).commit()
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