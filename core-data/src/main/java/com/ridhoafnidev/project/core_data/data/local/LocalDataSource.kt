package com.ridhoafnidev.project.core_data.data.local

import com.ridhoafnidev.project.core_data.data.local.entity.EventEntity
import com.ridhoafnidev.project.core_data.data.local.entity.ListEventsEntity
import com.ridhoafnidev.project.core_data.data.local.room.EventDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val eventDao: EventDao) {
    fun getEvents(): Flow<ListEventsEntity> = eventDao.getEventsAsFlow()
    suspend fun insertEvent(event: EventEntity) = eventDao.insertEvent(event)
}