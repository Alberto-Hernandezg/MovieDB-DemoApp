package com.pruebatecnica.di.modules.search

import com.search.domain.usecases.SearchFilmsUseCase
import org.koin.dsl.module

val searchUseCasesModule = module {
    factory {
        SearchFilmsUseCase(
            repository = get()
        )
    }
}
