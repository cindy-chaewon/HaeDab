package com.example.haedab.presentation.onboarding

import android.os.Bundle
import android.view.View
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentOnboardingFirstBinding

class OnboardingFirstFragment : BaseFragment<FragmentOnboardingFirstBinding>(FragmentOnboardingFirstBinding::bind, R.layout.fragment_onboarding_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.onboarding_frame, OnboardingSecondFragment()).commit()
        }

    }
}