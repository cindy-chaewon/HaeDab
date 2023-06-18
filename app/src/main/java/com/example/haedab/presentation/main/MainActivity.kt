package com.example.haedab.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.haedab.R
import com.example.haedab.common.BaseActivity
import com.example.haedab.databinding.ActivityMainBinding
import com.example.haedab.presentation.main.chatting.ChattingFragment
import com.example.haedab.presentation.onboarding.OnboardingFirstFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frame, ChattingFragment()).commit()
    }
}