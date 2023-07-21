package com.example.haedab.di

import android.content.Context
import androidx.room.Room
import com.example.haedab.data.local.MessageDao
import com.example.haedab.database.MessageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideChannelDao(messageDatabase: MessageDatabase): MessageDao {
        return messageDatabase.messageDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MessageDatabase {
        return Room.databaseBuilder(
            appContext,
            MessageDatabase::class.java,
            "message_database"
        ).build()
    }


}