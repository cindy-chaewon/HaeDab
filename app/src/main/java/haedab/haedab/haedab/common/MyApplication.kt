package haedab.haedab.haedab.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    private val PREFS_NAME = "MyPrefsFile"
    //private val KEY_FIRST_RUN = "firstRun"
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("first", true)
        editor.apply()

    }




}