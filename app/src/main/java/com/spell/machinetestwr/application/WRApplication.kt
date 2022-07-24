package com.spell.machinetestwr.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WRApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: WRApplication
            private set
    }
}