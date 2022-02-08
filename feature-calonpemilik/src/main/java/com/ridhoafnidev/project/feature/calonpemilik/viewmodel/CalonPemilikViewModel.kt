package com.ridhoafnidev.project.feature.calonpemilik.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.PerumahanRepository
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_data.data.remote.response.CommonResponse
import com.ridhoafnidev.project.core_domain.model.calon_pemilik.ListCalonPemilik
import com.ridhoafnidev.project.core_domain.model.detail_calon_pemilik.DetailCalonPemilik
import com.ridhoafnidev.project.core_domain.model.status_pengajuan.ListStatusPengajuan
import com.ridhoafnidev.project.core_domain.model.status_pengajuan.StatusPengajuan
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CalonPemilikViewModel(
    private val perumahanRepository: PerumahanRepository
) : ViewModel() {

    private val _listCalonPemilik = MutableLiveData<ApiEvent<ListCalonPemilik>>()
    val listCalonPemilik: LiveData<ApiEvent<ListCalonPemilik>>
        get() = _listCalonPemilik

    private val _detailCalonPemilik = MutableLiveData<ApiEvent<DetailCalonPemilik>>()
    val detailCalonPemilik: LiveData<ApiEvent<DetailCalonPemilik>>
        get() = _detailCalonPemilik

    private val _listStatusPengajuan = MutableLiveData<ApiEvent<ListStatusPengajuan>>()
    val listStatusPengajuan: LiveData<ApiEvent<ListStatusPengajuan>>
        get() = _listStatusPengajuan

    private val _updateStatusPengajuan = MutableLiveData<ApiEvent<CommonResponse>>()
    val updateStatusPengajuan: LiveData<ApiEvent<CommonResponse>>
        get() = _updateStatusPengajuan

    fun getCalonPemilikAll() {
        viewModelScope.launch {
            perumahanRepository.calonPemilikGetAll()
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _listCalonPemilik.value = it }
        }
    }

    fun getDetailCalonPemilik(id: Int) {
        viewModelScope.launch {
            perumahanRepository.getDetailCalonPemilik(id)
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _detailCalonPemilik.value = it }
        }
    }

    fun getStatusPengajuanAll() {
        viewModelScope.launch {
            perumahanRepository.statusPengajuanGetAll()
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _listStatusPengajuan.value = it }
        }
    }

    fun updateStatusPengajuan(
        id: Int,
        statusPengajuan: StatusPengajuan
    ) {
        viewModelScope.launch {
            perumahanRepository.updateStatusPengajuan(id, statusPengajuan)
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _updateStatusPengajuan.value = it }
        }
    }
}