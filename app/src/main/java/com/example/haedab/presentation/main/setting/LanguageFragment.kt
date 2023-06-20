package com.example.haedab.presentation.main.setting

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentLanguageBinding
import com.example.haedab.databinding.FragmentSettingBinding

class LanguageFragment: BaseFragment<FragmentLanguageBinding>(FragmentLanguageBinding::bind, R.layout.fragment_language) {

    private var languageList: ArrayList<Language> = arrayListOf(
        Language("한국어", R.drawable.language_1),
        Language("English", R.drawable.language_2),
        Language("हिन्दी", R.drawable.language_3),
        Language("日本語", R.drawable.language_4),
        Language("Deutsch", R.drawable.language_5),
        Language("Français", R.drawable.language_6),
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //리사이클러뷰
        val languageAdapter = LanguageAdapter(languageList)
        binding.languageRv.apply {
            adapter = languageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false )
            addItemDecoration(LanguageVerticalItemDecoration())
        }

    }
}