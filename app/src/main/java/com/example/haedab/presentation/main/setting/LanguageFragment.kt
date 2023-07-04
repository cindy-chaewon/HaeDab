package com.example.haedab.presentation.main.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentLanguageBinding
import com.example.haedab.databinding.FragmentSettingBinding
import com.example.haedab.presentation.main.chatting.ChattingActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class LanguageFragment: BaseFragment<FragmentLanguageBinding>(FragmentLanguageBinding::bind, R.layout.fragment_language) {

    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null

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

        binding.applicationBtn.visibility = INVISIBLE


        //리사이클러뷰
        val languageAdapter = LanguageAdapter(languageList)
        binding.languageRv.apply {
            adapter = languageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false )
            addItemDecoration(LanguageVerticalItemDecoration())
        }

        languageAdapter.setOnItemClickListener(object : LanguageAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                binding.applicationBtn.visibility = VISIBLE
            }
        })

        binding.backBtn.setOnClickListener {
            chattingActivity!!.back()
        }

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it){} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }
}