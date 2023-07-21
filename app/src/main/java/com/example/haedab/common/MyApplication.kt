package com.example.haedab.common

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }


}