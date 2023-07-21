package com.example.haedab.presentation.main.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.common.SETTING
import com.example.haedab.databinding.FragmentCategoryBinding
import com.example.haedab.databinding.FragmentSettingBinding
import com.example.haedab.presentation.main.category.CategoryFragment
import com.example.haedab.presentation.main.chatting.ChattingActivity
import com.example.haedab.presentation.main.chatting.ChattingFragment
import com.example.haedab.presentation.onboarding.OnboardingSecondFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment: BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting) {

    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.setting1.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, LanguageFragment()).addToBackStack(null).commit()
        }

        binding.setting2.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.chatting_frame, UseFirstFragment()).addToBackStack(SETTING.toString()).commit()
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