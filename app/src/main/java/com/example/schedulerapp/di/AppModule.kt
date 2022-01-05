package com.plcoding.mvvmtodoapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.mvvmtodoapp.data.AppDatabase
import com.plcoding.mvvmtodoapp.data.AppInfoRepository
import com.plcoding.mvvmtodoapp.data.AppInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "app_db").build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: AppDatabase): AppInfoRepository {
        return AppInfoRepositoryImpl(db.dao)
    }
}