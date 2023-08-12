package haedab.haedab.haedab.presentation.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
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
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    /*private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 버튼 이벤트 처리
            finish()
        }
    }*/
    private val CHATTING_ACTIVITY_REQUEST_CODE = 123

    private val viewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //this.onBackPressedDispatcher.addCallback(this, callback)
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

    /*fun startChattingActivity() {
        val intent = Intent(this, ChattingActivity::class.java)
        startActivityForResult(intent, CHATTING_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CHATTING_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // If ChattingActivity finished successfully, finish MainActivity too.
            finish()
        }
    }*/

    /*fun finishChattingActivity() {
        val chattingActivityIntent = Intent(this, ChattingActivity::class.java)
        chattingActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        finish()
    }*/


}