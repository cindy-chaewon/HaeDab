package haedab.haedab.haedab.presentation.main.setting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import haedab.haedab.haedab.R
import haedab.haedab.haedab.common.BaseFragment
import haedab.haedab.haedab.common.MyApplication
import haedab.haedab.haedab.databinding.FragmentLanguageBinding
import haedab.haedab.haedab.presentation.main.chatting.ChattingActivity
import haedab.haedab.haedab.presentation.splash.SplashActivity
import java.util.*
import java.util.Locale.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class LanguageFragment: BaseFragment<FragmentLanguageBinding>(FragmentLanguageBinding::bind, R.layout.fragment_language) {

    private val PREFS_NAME = "MyPrefsFile"
    lateinit var mAdView : AdView
    var chattingActivity: ChattingActivity?=null

    private var configuration: Configuration = Configuration()

    private var languageList: ArrayList<Language> = arrayListOf(
        Language("한국어", R.drawable.language_1),
        Language("English", R.drawable.language_2),
        Language("हिन्दी", R.drawable.language_3),
        Language("日本語", R.drawable.language_4),
        Language("Deutsch", R.drawable.language_5),
        Language("Français", R.drawable.language_6),
    )



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.applicationBtn.visibility = INVISIBLE


        //리사이클러뷰
        val languageAdapter = LanguageAdapter(languageList)
        binding.languageRv.apply {
            adapter = languageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false )
            addItemDecoration(LanguageVerticalItemDecoration())
        }


        languageAdapter.setOnItemClickListener(object : LanguageAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                binding.applicationBtn.visibility = VISIBLE
                when(position){
                    0 -> {configuration.locale = Locale.KOREA
                        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("lan", "ko")
                        editor.apply()
                    }
                    1 -> {configuration.locale = Locale.US
                        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("lan", "en")
                        editor.apply()
                    }
                    2 -> {// 인도의 Locale 객체 생성
                        val indianLocale = Locale("bn", "IN")
                        // 앱의 기본 Locale 설정 변경
                        Locale.setDefault(indianLocale)
                        configuration.setLocale(indianLocale)
                        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("lan", "hi")
                        editor.apply()
                    }
                    3 -> {configuration.locale = Locale.JAPAN
                        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("lan", "ja")
                        editor.apply()
                    }
                    4 -> {
                        val gerLocale = Locale("en", "DE")
                        Locale.setDefault(gerLocale)
                        configuration.setLocale(gerLocale)
                        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("lan", "de")
                        editor.apply()
                    }
                    5 -> {configuration.locale = Locale.FRANCE
                        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("lan", "de")
                        editor.apply()
                    }

                }

            }
        })

        binding.backBtn.setOnClickListener {
            chattingActivity!!.back()
        }

        //애드몹 광고
        //모바일광고 SDK 초기화
        context?.let { MobileAds.initialize(it){} }
        //광고 띄우기
        mAdView = binding.admobBanner
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //언어 선택 적용
        binding.applicationBtn.setOnClickListener {
            resources.updateConfiguration(configuration,resources.displayMetrics)
            val intent = Intent(context, SplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            /*activity?.let {
                val intent = Intent(context, SplashActivity::class.java)
                startActivity(intent)
            }*/
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chattingActivity = context as ChattingActivity
    }
}