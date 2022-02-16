package com.ridhoafnidev.project.feature.home

import com.ridhoafnidev.project.feature.home.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule
    get() = module {
        viewModel { AuthViewModel(get()) }
    }