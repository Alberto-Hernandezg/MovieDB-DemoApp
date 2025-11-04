package com.pruebatecnica.di.modules.filmdetail

import com.shared.presentation.viewmodel.FilmDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filmDetailsPresentationModule = module {
    viewModel {
        FilmDetailViewModel(
            getFilmDetailsUseCase = get()
        )
    }
}
