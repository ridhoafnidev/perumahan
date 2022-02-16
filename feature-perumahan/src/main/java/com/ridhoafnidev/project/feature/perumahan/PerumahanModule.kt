package com.ridhoafnidev.project.feature.perumahan

import com.ridhoafnidev.project.feature.perumahan.viewmodel.PerumahanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createEventModule
    get() = module {
        viewModel { PerumahanViewModel(get()) }
    }