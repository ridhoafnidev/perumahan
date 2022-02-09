package com.ridhoafnidev.project.feature.auth

import com.ridhoafnidev.project.feature.auth.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule
    get() = module {
        viewModel { AuthViewModel(get()) }
    }