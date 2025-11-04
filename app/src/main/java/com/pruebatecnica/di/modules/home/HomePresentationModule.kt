package com.pruebatecnica.di.modules.home

import com.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homePresentationModule = module {
    viewModel {
        HomeViewModel(
            getHomeFilmListUseCase = get()
        )
    }
}
