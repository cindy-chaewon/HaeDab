package com.example.haedab.presentation.onboarding


import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.haedab.R
import com.example.haedab.common.BaseActivity
import com.example.haedab.databinding.ActivityOnboardingBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(ActivityOnboardingBinding::inflate) {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.onboarding_frame, OnboardingFirstFragment()).commit()

        //애드몹 광고
        //모바일광고 SDK 초기화
        MobileAds.initialize(this){}
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}