package com.pruebatecnica.di.modules.genres

import com.genres.data.datasource.GenresDataSource
import com.genres.data.datasource.GenresRemoteDataSource
import com.pruebatecnica.managers.API_KEY
import org.koin.dsl.module

val genresDataSourcesModule = module {
    single<GenresDataSource> {
        GenresRemoteDataSource(
            service = get(),
            apiKey = API_KEY
        )
    }
}
