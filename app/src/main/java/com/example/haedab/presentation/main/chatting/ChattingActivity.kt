package com.example.haedab.presentation.main.chatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.haedab.R
import com.example.haedab.common.BaseActivity
import com.example.haedab.databinding.ActivityChattingBinding
import com.example.haedab.databinding.ActivityOnboardingBinding
import com.example.haedab.presentation.main.MainActivity
import com.example.haedab.presentation.onboarding.OnboardingActivity
import com.example.haedab.presentation.onboarding.OnboardingFirstFragment
import com.example.haedab.viewmodel.ChatViewModel
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class ChattingActivity : BaseActivity<ActivityChattingBinding>(ActivityChattingBinding::inflate) {
    lateinit var mAdView: AdView

    private var mInterstitialAd: InterstitialAd? = null

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.chatting_frame, ChattingFragment()).commit()
        setupInterstitialAd()

    }

    fun admob(){
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this@ChattingActivity)
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
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

        val randomAdId = getRandomAdId()

        InterstitialAd.load(this,
            randomAdId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("DEBUG: ", adError.message.toString())
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

    // 랜덤으로 애드몹 광고 id 출력 함수+
    private fun getRandomAdId(): String {
        val adIds = listOf(
            "ca-app-pub-4004046235562178/3211382774",
            "ca-app-pub-4004046235562178/1878119804",
            "ca-app-pub-4004046235562178/2289221337"
        )

        val random = Random()
        val randomIndex = random.nextInt(adIds.size)
        return adIds[randomIndex]
    }


    /*override fun onDestroy() {
        super.onDestroy()
        viewModel.viewModelScope.launch {
            viewModel.deleteAll()
        }
    }*/

    /*override fun onStop() {
        super.onStop()
        viewModel.viewModelScope.launch {
            viewModel.deleteAll()
        }
    }*/

    /*override fun onPause() {
        super.onPause()
        viewModel.viewModelScope.launch {
            viewModel.deleteAll()
        }
    }*/
}