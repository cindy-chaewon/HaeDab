package haedab.haedab.haedab.presentation.onboarding

import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.databinding.FragmentOnboardingSecondBinding

@AndroidEntryPoint
class OnboardingSecondFragment:
    BaseFragment<FragmentOnboardingSecondBinding>(FragmentOnboardingSecondBinding::bind, R.layout.fragment_onboarding_second) {

    lateinit var mAdView : AdView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.onboarding_frame, OnboardingThirdFragment()).commit()
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