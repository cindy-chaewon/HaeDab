package com.example.haedab.presentation.splash

import android.content.Intent
import android.os.Bundle
import com.example.haedab.common.BaseActivity
import com.example.haedab.databinding.ActivitySplashBinding
import com.example.haedab.presentation.main.MainActivity
import com.example.haedab.presentation.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val time: Long = 1800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        CoroutineScope(Dispatchers.IO).launch {
            delay(time)
            val intent = Intent(applicationContext, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}