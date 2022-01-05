package com.plcoding.mvvmtodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppInfo(appInfo: AppInfo)

    @Delete
    suspend fun deleteAppInfo(appInfo: AppInfo)

    @Query("SELECT * FROM app_info WHERE id = :id")
    suspend fun getAppInfoById(id: Int): AppInfo?

    @Query("SELECT * FROM app_info")
    fun getAppInfoList(): Flow<List<AppInfo>>
}