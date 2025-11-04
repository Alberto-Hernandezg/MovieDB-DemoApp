package com.pruebatecnica.di.modules.filmdetail

import com.shared.domain.usecases.GetFilmDetailsUseCase
import org.koin.dsl.module

val filmDetailsUseCasesModule = module {
    factory {
        GetFilmDetailsUseCase(
            repository = get()
        )
    }
}
