package com.ridhoafnidev.project.feature.calonpemilik

import com.ridhoafnidev.project.feature.calonpemilik.viewmodel.AuthViewModel
import com.ridhoafnidev.project.feature.calonpemilik.viewmodel.CalonPemilikViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calonPemilikModule = module {
    viewModel { CalonPemilikViewModel(get()) }
    viewModel { AuthViewModel(get()) }
}