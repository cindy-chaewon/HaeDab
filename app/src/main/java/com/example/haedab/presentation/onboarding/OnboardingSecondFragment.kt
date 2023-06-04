package com.example.haedab.presentation.onboarding

import android.os.Bundle
import android.view.View
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentOnboardingSecondBinding

class OnboardingSecondFragment:BaseFragment<FragmentOnboardingSecondBinding>(FragmentOnboardingSecondBinding::bind, R.layout.fragment_onboarding_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.onboarding_frame, OnboardingThirdFragment()).addToBackStack(null).commit()
        }

    }
}