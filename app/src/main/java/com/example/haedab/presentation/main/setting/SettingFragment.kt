package com.example.haedab.presentation.main.setting

import android.os.Bundle
import android.view.View
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentCategoryBinding
import com.example.haedab.databinding.FragmentSettingBinding
import com.example.haedab.presentation.main.category.CategoryFragment
import com.example.haedab.presentation.onboarding.OnboardingSecondFragment

class SettingFragment: BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.setting1.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_frame, LanguageFragment()).commit()
        }

    }
}