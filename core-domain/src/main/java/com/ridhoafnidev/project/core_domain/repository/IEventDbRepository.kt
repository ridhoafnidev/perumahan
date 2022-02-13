package com.ridhoafnidev.project.core_domain.repository

import com.ridhoafnidev.project.core_domain.model.Event
import com.ridhoafnidev.project.core_domain.model.ListEvents
import kotlinx.coroutines.flow.Flow

interface IEventDbRepository {
    fun getEvents(): Flow<ListEvents>
    suspend fun insertEvent(event: Event)
}