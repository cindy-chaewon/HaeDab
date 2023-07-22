package haedab.haedab.haedab.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope

import com.google.android.gms.ads.*
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.common.BaseActivity
import haedab.haedab.haedab.databinding.ActivityMainBinding
import haedab.haedab.haedab.presentation.main.category.CategoryFragment
import haedab.haedab.haedab.viewmodel.ChatViewModel
import kotlinx.coroutines.launch
import haedab.haedab.haedab.R

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){


    private val viewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frame, CategoryFragment()).commit()


    }

    companion object {
        fun startMainActivity(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.viewModelScope.launch {
            viewModel.deleteAll()
        }
    }


}