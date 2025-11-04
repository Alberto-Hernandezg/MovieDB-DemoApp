package com.pruebatecnica.di.modules.home

import com.home.domain.usecases.GetHomeFilmListUseCase
import org.koin.dsl.module

val homeUseCasesModule = module {
    factory {
        GetHomeFilmListUseCase(
            repository = get()
        )
    }
}
