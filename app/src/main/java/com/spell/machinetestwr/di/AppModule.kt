package com.spell.machinetestwr.di

import android.content.Context
import com.spell.machinetestwr.localdatabase.dao.UserDetailsDao
import com.spell.machinetestwr.remotenetwork.ApiService
import com.spell.machinetestwr.repository.AppRepository
import com.spell.machinetestwr.repository.LocalDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context):Context{
        return  context
    }

    @Singleton
    @Provides
    fun providesRepository(apiService: ApiService) = AppRepository(apiService)

    @Singleton
    @Provides
    fun providesLocalRepository(dao: UserDetailsDao) = LocalDatabaseRepository(dao)

}