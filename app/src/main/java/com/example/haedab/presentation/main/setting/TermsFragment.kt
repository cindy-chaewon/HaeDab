package com.example.haedab.presentation.main.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import com.example.haedab.R
import com.example.haedab.common.BaseFragment
import com.example.haedab.databinding.FragmentTermsBinding
import com.example.haedab.presentation.main.chatting.ChattingActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsFragment: BaseFragment<FragmentTermsBinding>(FragmentTermsBinding::bind, R.layout.fragment_terms) {

    var chattingActivity: ChattingActivity?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            chattingActivity!!.back()
        }

        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        binding.webview.loadUrl("https://docs.google.com/document/d/1fYL4_1dlwCgcAY7tKmVZu12mmtavRMmH3_YPuDy7KCo/edit?usp=sharing")


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }
}