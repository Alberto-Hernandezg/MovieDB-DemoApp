package com.pruebatecnica.di.modules.home

import com.home.data.datasource.HomeFilmDataSource
import com.home.data.datasource.HomeFilmRemoteDataSource
import com.pruebatecnica.managers.API_KEY
import org.koin.dsl.module

val homeDataSourcesModule = module {
    single<HomeFilmDataSource> {
        HomeFilmRemoteDataSource(
            service = get(),
            apiKey = API_KEY
        )
    }
}
