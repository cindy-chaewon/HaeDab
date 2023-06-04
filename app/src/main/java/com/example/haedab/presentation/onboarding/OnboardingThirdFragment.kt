package com.example.haedab.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentOnboardingThirdBinding
import com.example.haedab.presentation.main.MainActivity

class OnboardingThirdFragment: BaseFragment<FragmentOnboardingThirdBinding>(FragmentOnboardingThirdBinding::bind, R.layout.fragment_onboarding_third) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}