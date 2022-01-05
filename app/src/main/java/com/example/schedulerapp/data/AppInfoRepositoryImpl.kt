package com.plcoding.mvvmtodoapp.data

import kotlinx.coroutines.flow.Flow

class AppInfoRepositoryImpl(
    private val dao: AppInfoDao
): AppInfoRepository {

    override suspend fun insertAppInfo(appInfo: AppInfo) {
        dao.insertAppInfo(appInfo)
    }

    override suspend fun deleteAppInfo(appInfo: AppInfo) {
        dao.deleteAppInfo(appInfo)
    }

    override suspend fun getAppInfoById(id: Int): AppInfo? {
        return dao.getAppInfoById(id)
    }

    override fun getAppInfoList(): Flow<List<AppInfo>> {
        return dao.getAppInfoList()
    }
}