package com.example.haedab.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.haedab.R
import com.example.haedab.common.BaseActivity
import com.example.haedab.databinding.ActivityMainBinding
import com.example.haedab.presentation.main.category.CategoryFragment
import com.example.haedab.presentation.main.chatting.ChattingFragment
import com.example.haedab.presentation.main.setting.SettingFragment
import com.example.haedab.presentation.onboarding.OnboardingActivity
import com.example.haedab.presentation.onboarding.OnboardingFirstFragment
import com.example.haedab.viewmodel.ChatViewModel
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){


    private val viewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryFragment()).commit()


    }

    companion object {
        fun startMainActivity(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.viewModelScope.launch {
            viewModel.deleteAll()
        }
    }


}