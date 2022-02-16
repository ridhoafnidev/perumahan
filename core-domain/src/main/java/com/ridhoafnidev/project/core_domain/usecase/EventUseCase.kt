package com.ridhoafnidev.project.core_domain.usecase

import com.ridhoafnidev.project.core_domain.model.Event
import com.ridhoafnidev.project.core_domain.model.ListEvents
import kotlinx.coroutines.flow.Flow

interface EventUseCase {
    fun getEvents() : Flow<ListEvents>
    suspend fun insertEvent(event: Event)
}