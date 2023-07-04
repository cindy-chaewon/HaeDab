package com.example.haedab.presentation.main.chatting

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentChattingBinding
import com.example.haedab.databinding.FragmentOnboardingFirstBinding
import com.example.haedab.presentation.main.MainActivity
import com.example.haedab.presentation.main.category.CategoryFragment
import com.example.haedab.presentation.main.setting.SettingFragment
import com.example.haedab.presentation.onboarding.OnboardingSecondFragment
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class ChattingFragment: BaseFragment<FragmentChattingBinding>(FragmentChattingBinding::bind, R.layout.fragment_chatting)  {

    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }

}