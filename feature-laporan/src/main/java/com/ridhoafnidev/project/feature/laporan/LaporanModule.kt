package com.ridhoafnidev.project.feature.laporan

import com.ridhoafnidev.project.feature.laporan.viewmodel.LaporanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val laporanModule = module {
    viewModel { LaporanViewModel(get()) }
}