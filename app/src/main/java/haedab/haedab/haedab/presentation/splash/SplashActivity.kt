package haedab.haedab.haedab.presentation.splash

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.common.BaseActivity
import haedab.haedab.haedab.databinding.ActivitySplashBinding
import haedab.haedab.haedab.presentation.onboarding.OnboardingActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val time: Long = 1800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        CoroutineScope(Dispatchers.IO).launch {
            delay(time)
            val intent = Intent(applicationContext, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}