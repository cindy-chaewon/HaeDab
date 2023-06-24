package com.example.haedab.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.haedab.R
import com.example.haedab.common.BaseActivity
import com.example.haedab.databinding.ActivityMainBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment
import com.example.haedab.presentation.onboarding.OnboardingActivity
import com.example.haedab.presentation.onboarding.OnboardingFirstFragment
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    lateinit var mAdView : AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        supportFragmentManager.beginTransaction().replace(R.id.main_frame, ChattingFragment()).commit()

        //애드몹 광고
        //모바일광고 SDK 초기화
        MobileAds.initialize(this){}
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


    }

}