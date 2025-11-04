package com.pruebatecnica.di.modules.search

import com.search.data.repository.FilmSearchRepositoryImpl
import com.search.domain.repository.FilmSearchRepository
import org.koin.dsl.module

val searchRepositoriesModule = module {
    single<FilmSearchRepository> {
        FilmSearchRepositoryImpl(
            remoteDataSource = get()
        )
    }
}
