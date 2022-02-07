package com.ridhoafnidev.project.feature.calonpemilik.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.PerumahanRepository
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_domain.model.calon_pemilik.ListCalonPemilik
import com.ridhoafnidev.project.core_domain.model.detail_calon_pemilik.DetailCalonPemilik
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
}