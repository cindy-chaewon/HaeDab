package haedab.haedab.haedab.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import haedab.haedab.haedab.data.local.MessageDao
import haedab.haedab.haedab.database.MessageDatabase
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