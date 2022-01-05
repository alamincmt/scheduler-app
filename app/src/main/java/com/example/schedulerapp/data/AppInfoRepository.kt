package com.plcoding.mvvmtodoapp.data

import kotlinx.coroutines.flow.Flow

interface AppInfoRepository {

    suspend fun insertAppInfo(appInfo: AppInfo)

    suspend fun deleteAppInfo(appInfo: AppInfo)

    suspend fun getAppInfoById(id: Int): AppInfo?

    fun getAppInfoList(): Flow<List<AppInfo>>
}