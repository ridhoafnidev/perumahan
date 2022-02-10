package com.ridhoafnidev.project.feature.perumahan.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.PerumahanRepository
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_data.domain.Event
import com.ridhoafnidev.project.core_domain.model.tipe_rumah.ListTipePerumahanGetAll
import com.ridhoafnidev.project.core_domain.usecase.EventUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class PerumahanViewModel(
    private val perumahanRepository: PerumahanRepository
) : ViewModel() {
    private val _listTipeRumah = MutableLiveData<ApiEvent<ListTipePerumahanGetAll>>()
    val listTipeRumah: LiveData<ApiEvent<ListTipePerumahanGetAll>>
        get() = _listTipeRumah

    fun tipeRumahGetAll() {
        viewModelScope.launch {
            perumahanRepository
                .tipeRumahGetAll()
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _listTipeRumah.value = it }
        }
    }
}