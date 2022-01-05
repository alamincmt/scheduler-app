package com.plcoding.mvvmtodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "app_info")
data class AppInfo(
    val app_name: String,
    val app_package_name: String,
    val app_icon: String?,
    val alarmActiveStatus: Boolean,
    val alarmTime: Date,
    @PrimaryKey val id: Int? = null
)
