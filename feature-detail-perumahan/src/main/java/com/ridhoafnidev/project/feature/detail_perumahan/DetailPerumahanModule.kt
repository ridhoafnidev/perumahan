package com.ridhoafnidev.project.feature.detail_perumahan

import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.AuthViewModel
import com.ridhoafnidev.project.feature.detail_perumahan.viewmodel.DetailPerumahanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailPerumahanModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { DetailPerumahanViewModel(get()) }
}