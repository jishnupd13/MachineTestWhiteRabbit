package com.spell.machinetestwr

import android.content.Context
import com.spell.machinetestwr.application.WRApplication
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class Utils(val context: Context) {

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface StringProviderEntryPoint {
        fun preference(): String
    }

    fun getSharedPreference(): String {

        val hiltEntryPoint = EntryPointAccessors.fromApplication(
           context,
            StringProviderEntryPoint::class.java
        )

        return hiltEntryPoint.preference()
    }
}