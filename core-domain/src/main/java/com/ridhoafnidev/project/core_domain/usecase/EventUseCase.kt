package com.ridhoafnidev.project.core_domain.usecase

import com.ridhoafnidev.project.core_data.domain.Event
import com.ridhoafnidev.project.core_data.domain.ListEvents
import kotlinx.coroutines.flow.Flow

interface EventUseCase {
    fun getEvents() : Flow<ListEvents>
    suspend fun insertEvent(event: Event)
}