package haedab.haedab.haedab.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.databinding.FragmentOnboardingThirdBinding
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity


@AndroidEntryPoint

class OnboardingThirdFragment: BaseFragment<FragmentOnboardingThirdBinding>(
    FragmentOnboardingThirdBinding::bind, R.layout.fragment_onboarding_third) {

    lateinit var mAdView: AdView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ChattingActivity::class.java)
                startActivity(intent)
            }
        }

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it) {} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}