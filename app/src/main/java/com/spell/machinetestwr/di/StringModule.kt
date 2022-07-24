package com.spell.machinetestwr.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StringModule {

    @Singleton
    @Provides
    fun provideString(): String {
        return "hello world success"
    }
}