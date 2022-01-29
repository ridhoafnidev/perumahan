package com.ridhoafnidev.project.feature.perumahan

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createEventModule
    get() = module {
        viewModel { PerumahanViewModel(get()) }
    }