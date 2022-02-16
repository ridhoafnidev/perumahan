package com.ridhoafnidev.project.feature.detail_perumahan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.PerumahanRepository
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_data.data.remote.response.CommonResponse
import com.ridhoafnidev.project.core_domain.model.ListPerumahanGetAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File

class CheckoutViewModel(
    private val perumahanRepository: PerumahanRepository
) : ViewModel() {

    private val _listPerumahan = MutableLiveData<ApiEvent<ListPerumahanGetAll>>()
    val listPerumahan: LiveData<ApiEvent<ListPerumahanGetAll>>
        get() = _listPerumahan

    private val _insertCalonPemilikResponse = MutableLiveData<ApiEvent<CommonResponse>>()
    val insertCalonPemilikResponse: LiveData<ApiEvent<CommonResponse>>
        get() = _insertCalonPemilikResponse

    fun getListPerumahan(id: Int) {
        viewModelScope.launch {
            perumahanRepository.getPerumahan(id)
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _listPerumahan.value = it }
        }
    }

    fun insertCalonPemilik(
        konsumenId: Int,
        tipePerumahanId: Int,
        rumahId: Int,
        jumlahDp: Int,
        buktiTransfer: File
    ) {
        viewModelScope.launch {
            perumahanRepository.insertCalonPemilik(
                konsumenId = konsumenId,
                tipePerumahanId = tipePerumahanId,
                rumahId = rumahId,
                jumlahDp = jumlahDp,
                buktiTransfer = buktiTransfer
            ).onStart { emit(ApiEvent.OnProgress()) }
             .collect { _insertCalonPemilikResponse.value = it }
        }
    }
}