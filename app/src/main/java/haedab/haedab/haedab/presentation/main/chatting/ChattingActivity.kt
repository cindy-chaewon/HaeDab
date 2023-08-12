package haedab.haedab.haedab.presentation.main.chatting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.common.BaseActivity
import haedab.haedab.haedab.databinding.ActivityChattingBinding
import haedab.haedab.haedab.presentation.main.MainActivity
import haedab.haedab.haedab.viewmodel.ChatViewModel
import java.util.*
import haedab.haedab.haedab.R

@AndroidEntryPoint
class ChattingActivity : BaseActivity<ActivityChattingBinding>(ActivityChattingBinding::inflate) {
    lateinit var mAdView: AdView
    private val PREFS_NAME = "MyPrefsFile"
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

    override fun onDestroy() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        super.onDestroy()
    }

    fun onBack(){
            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()

    }
}