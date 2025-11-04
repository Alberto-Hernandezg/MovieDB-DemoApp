package com.pruebatecnica.di.modules.filmdetail

import com.pruebatecnica.managers.API_KEY
import com.shared.data.datasource.FilmDetailsDataSource
import com.shared.data.datasource.FilmDetailsRemoteDataSource
import org.koin.dsl.module

val filmDetailsDataSourcesModule = module {
    single<FilmDetailsDataSource> {
        FilmDetailsRemoteDataSource(
            service = get(),
            apiKey = API_KEY
        )
    }
}
