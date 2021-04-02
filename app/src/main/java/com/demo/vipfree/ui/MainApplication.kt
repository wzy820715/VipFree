package com.demo.vipfree.ui

import android.app.Application
import android.content.Context

class MainApplication : Application() {
    companion object {
        lateinit var sMainApplication: Context

        fun getContext(): Context? {
            return sMainApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        sMainApplication = this
    }


}