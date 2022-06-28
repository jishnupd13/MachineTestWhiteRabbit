package com.spell.machinetestwr.di

import android.content.Context
import androidx.room.Room
import com.spell.machinetestwr.localdatabase.UserDetailsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        UserDetailsDatabase::class.java,
        "user_details_db"
    ).build()

    @Singleton
    @Provides
    fun provideUserDetailsDao(db: UserDetailsDatabase) = db.userDetailsDao()
}