package com.example.haedab.presentation.main.chatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.haedab.R
import com.example.haedab.common.BaseActivity
import com.example.haedab.databinding.ActivityChattingBinding
import com.example.haedab.databinding.ActivityOnboardingBinding
import com.example.haedab.presentation.main.MainActivity
import com.example.haedab.presentation.onboarding.OnboardingActivity
import com.example.haedab.presentation.onboarding.OnboardingFirstFragment
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class ChattingActivity : BaseActivity<ActivityChattingBinding>(ActivityChattingBinding::inflate) {
    lateinit var mAdView: AdView

    private var mInterstitialAd: InterstitialAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.chatting_frame, ChattingFragment()).commit()
        setupInterstitialAd()

    }

    fun admob(){
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this@ChattingActivity)
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                fun onAdClicked() {
                    Log.d("DEBUG: ", "Ad was clicked.")
                }

                override fun onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    Log.d("DEBUG: ", "Ad dismissed fullscreen content.")
                    gotoMainActivity()
                    mInterstitialAd = null
                    setupInterstitialAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e("DEBUG: ", "Ad failed to show fullscreen content.")
                    mInterstitialAd = null
                }

                override fun onAdImpression() {
                    Log.d("DEBUG: ", "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("DEBUG: ", "Ad showed fullscreen content.")
                }
            }
        } else {
            gotoMainActivity()
        }
    }

    private fun gotoMainActivity() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun setupInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("DEBUG: ", adError?.message.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("DEBUG: ", "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
    }

    fun back(){
        onBackPressed()
    }
}