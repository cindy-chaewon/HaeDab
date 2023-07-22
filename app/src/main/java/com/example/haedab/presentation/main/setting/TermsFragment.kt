package com.example.haedab.presentation.main.setting

import android.content.Context
import android.content.res.Configuration
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
import java.util.*

@AndroidEntryPoint
class TermsFragment: BaseFragment<FragmentTermsBinding>(FragmentTermsBinding::bind, R.layout.fragment_terms) {

    var chattingActivity: ChattingActivity?=null
    private var configuration: Configuration = Configuration()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            chattingActivity!!.back()
        }

        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        if(configuration.locale == Locale.KOREA){
            binding.webview.loadUrl("https://docs.google.com/document/d/1fYL4_1dlwCgcAY7tKmVZu12mmtavRMmH3_YPuDy7KCo/edit?usp=sharing")
        }
        else if (configuration.locale == Locale.US){
            binding.webview.loadUrl("https://docs.google.com/document/d/1B6qt7vbBOtfnl-n2-v-mb1YpJnpmx765gmD2u8copnk/edit?usp=sharing")
        }
        else if(configuration.locale == Locale.FRANCE){
            binding.webview.loadUrl("https://docs.google.com/document/d/1Vs0gP8W1rDooKUnv9hFUK5CD4Debag8yu2xnBfGxv90/edit?usp=sharing")
        }
        else if (configuration.locale == Locale.JAPAN){
            binding.webview.loadUrl("https://docs.google.com/document/d/1WkXD0k9451L81q-ua7FMus0g9hYFnjohz4YbdjXwops/edit?usp=sharing")
        }
        else if( configuration.locale == Locale.GERMANY){
            binding.webview.loadUrl("https://docs.google.com/document/d/1nJ2-O6JN38qRa3HOAqsePVfzSs10tQJthR-sUtjR09o/edit?usp=sharing")
        }
        else {
            binding.webview.loadUrl("https://docs.google.com/document/d/1B6qt7vbBOtfnl-n2-v-mb1YpJnpmx765gmD2u8copnk/edit?usp=sharing")
        }



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }
}