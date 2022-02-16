package com.ridhoafnidev.project.feature.laporan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.PerumahanRepository
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.laporan.ListLaporan
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LaporanViewModel(
    private val perumahanRepository: PerumahanRepository
) : ViewModel() {
    private val _listLaporan = MutableLiveData<ApiEvent<ListLaporan>>()
    val listLaporan: LiveData<ApiEvent<ListLaporan>>
        get() = _listLaporan

    fun getLaporan(
        start: String,
        end: String
    ) {
        viewModelScope.launch {
            perumahanRepository.getLaporan(start, end)
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _listLaporan.value = it }
        }
    }
}