package com.pruebatecnica.di.modules.search

import com.pruebatecnica.managers.API_KEY
import com.search.data.datasource.FilmSearchDataSource
import com.search.data.datasource.FilmSearchRemoteDataSource
import org.koin.dsl.module

val searchDataSourcesModule = module {
    single<FilmSearchDataSource> {
        FilmSearchRemoteDataSource(
            service = get(),
            apiKey = API_KEY
        )
    }
}
