package haedab.haedab.haedab.presentation.onboarding

import android.os.Bundle
import android.view.View

import com.google.android.gms.ads.*
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.databinding.FragmentOnboardingFirstBinding
import haedab.haedab.haedab.R
@AndroidEntryPoint
class OnboardingFirstFragment : BaseFragment<FragmentOnboardingFirstBinding>(FragmentOnboardingFirstBinding::bind, R.layout.fragment_onboarding_first) {

    lateinit var mAdView : AdView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.onboarding_frame, OnboardingSecondFragment()).addToBackStack(null).commit()
        }

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it){} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



    }
}