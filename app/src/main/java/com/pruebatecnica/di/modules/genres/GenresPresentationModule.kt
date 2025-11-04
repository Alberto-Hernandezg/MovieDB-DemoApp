package com.pruebatecnica.di.modules.genres

import com.genres.presentation.viewmodel.GenresViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val genresPresentationModule = module {
    viewModel {
        GenresViewModel(
            getGenreListUseCase = get(),
            getFilmsByGenreUseCase = get()
        )
    }
}
