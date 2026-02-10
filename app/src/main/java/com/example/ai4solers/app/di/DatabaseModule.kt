package com.example.ai4solers.app.di

import android.content.Context
import androidx.room.Room
import com.example.ai4solers.core.common.Constants
import com.example.ai4solers.data.local.db.AppDatabase
import com.example.ai4solers.data.local.db.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    //Tao database voi hilt
    @Provides
    @Singleton //App chi ket noi 1 database
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        )
            //neu quen tang version database khi update entity thi xoa du lieu db cu va tao db moi voi ham nay
            //.fallbackToDestructiveMigration()
            .build()
    }

    //Lay DAO
    @Provides
    @Singleton
    fun provideHistoryDao(database: AppDatabase): HistoryDao {
        return database.historyDao()
    }

}