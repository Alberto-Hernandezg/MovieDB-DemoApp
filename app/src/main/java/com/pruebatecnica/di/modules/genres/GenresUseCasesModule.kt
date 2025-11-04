package com.pruebatecnica.di.modules.genres

import com.genres.domain.usecases.GetFilmsByGenreUseCase
import com.genres.domain.usecases.GetGenreListUseCase
import org.koin.dsl.module

val genresUseCasesModule = module {
    factory {
        GetGenreListUseCase(
            repository = get()
        )
    }

    factory {
        GetFilmsByGenreUseCase(
            repository = get()
        )
    }
}
