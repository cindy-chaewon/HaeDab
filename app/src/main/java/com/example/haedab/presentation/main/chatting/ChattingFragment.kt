package com.example.haedab.presentation.main.chatting

import android.os.Bundle
import android.view.View
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentChattingBinding
import com.example.haedab.databinding.FragmentOnboardingFirstBinding
import com.example.haedab.presentation.main.category.CategoryFragment
import com.example.haedab.presentation.main.setting.SettingFragment
import com.example.haedab.presentation.onboarding.OnboardingSecondFragment

class ChattingFragment: BaseFragment<FragmentChattingBinding>(FragmentChattingBinding::bind, R.layout.fragment_chatting)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기 버튼 눌렀을때
        binding.backBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryFragment()).commit()
        }

        binding.settingBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.main_frame, SettingFragment()).addToBackStack(null).commit()
        }


    }
}