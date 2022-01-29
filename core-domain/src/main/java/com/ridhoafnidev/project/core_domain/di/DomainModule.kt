package com.ridhoafnidev.project.core_data.di

import com.ridhoafnidev.project.core_domain.usecase.AuthInteractor
import com.ridhoafnidev.project.core_domain.usecase.AuthUseCase
import com.ridhoafnidev.project.core_domain.usecase.EventDbInteractor
import com.ridhoafnidev.project.core_domain.usecase.EventUseCase
import org.koin.dsl.module

val domainModule
    get() = module {
        factory<EventUseCase> { EventDbInteractor(get()) }
        factory<AuthUseCase> { AuthInteractor( get() ) }
    }