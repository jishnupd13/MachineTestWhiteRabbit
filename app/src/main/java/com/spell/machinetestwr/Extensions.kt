package com.spell.machinetestwr

import com.spell.machinetestwr.application.WRApplication
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface StringProviderEntryPoint {
    fun preference(): String
}


fun String.toNormal(): String {

    val hiltEntryPoint = EntryPointAccessors.fromApplication(
        WRApplication.instance,
        StringProviderEntryPoint::class.java
    )
    return hiltEntryPoint.preference()
}