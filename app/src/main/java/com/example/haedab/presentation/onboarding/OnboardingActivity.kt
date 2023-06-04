package com.example.haedab.presentation.onboarding


import android.os.Bundle
import com.example.haedab.R
import com.example.haedab.common.BaseActivity
import com.example.haedab.databinding.ActivityOnboardingBinding

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(ActivityOnboardingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.onboarding_frame, OnboardingFirstFragment()).commit()

    }
}