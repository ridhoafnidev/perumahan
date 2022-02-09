package com.ridhoafnidev.project.core_data.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ridhoafnidev.project.core_data.BuildConfig
import com.ridhoafnidev.project.core_data.data.local.entity.AuthEntity
import com.ridhoafnidev.project.core_data.data.local.entity.EventEntity

@Database(
    entities = [EventEntity::class, AuthEntity::class],
    version = BuildConfig.schemaDatabaseVersion,
)

internal abstract class CoreDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun authDao(): AuthDao
}

