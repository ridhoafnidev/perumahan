package com.ridhoafnidev.project.feature.detail_perumahan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.PerumahanRepository
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.detail_tipe_rumah.DetailTipeRumah
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailPerumahanViewModel(
    private val perumahanRepository: PerumahanRepository
) : ViewModel() {

    private val _detailTipeRumah = MutableLiveData<ApiEvent<DetailTipeRumah>>()
    val detailTipeRumah: LiveData<ApiEvent<DetailTipeRumah>>
        get() = _detailTipeRumah

    fun getDetailTipeRumah(id: Int) {
        viewModelScope.launch {
            perumahanRepository.getDetailTipeRumah(id)
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _detailTipeRumah.value = it }
        }
    }
}