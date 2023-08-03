package haedab.haedab.haedab.presentation.onboarding


import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope

import com.google.android.gms.ads.AdView
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseActivity
import haedab.haedab.haedab.databinding.ActivityOnboardingBinding
import haedab.haedab.haedab.viewmodel.ChatViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(ActivityOnboardingBinding::inflate) {
    lateinit var mAdView : AdView

    private val viewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.onboarding_frame, OnboardingFirstFragment()).commit()

    }

    override fun onResume() {
        super.onResume()
        viewModel.viewModelScope.launch {
            viewModel.deleteAll()
        }
    }
}