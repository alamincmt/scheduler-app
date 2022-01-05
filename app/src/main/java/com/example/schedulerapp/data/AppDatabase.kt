package com.plcoding.mvvmtodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.schedulerapp.utils.Converters


@Database(entities = [AppInfo::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: AppInfoDao
}