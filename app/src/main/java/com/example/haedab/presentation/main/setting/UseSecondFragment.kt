package com.example.haedab.presentation.main.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentUseSecondBinding
import com.example.haedab.presentation.main.chatting.ChattingActivity
import com.example.haedab.presentation.onboarding.OnboardingSecondFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class UseSecondFragment:BaseFragment<FragmentUseSecondBinding>(FragmentUseSecondBinding::bind, R.layout.fragment_use_second) {

    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }
}