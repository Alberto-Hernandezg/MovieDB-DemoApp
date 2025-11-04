package com.pruebatecnica.di.modules.home

import com.home.domain.repository.HomeFilmRepository
import com.home.data.repository.HomeFilmRepositoryImpl
import org.koin.dsl.module

val homeRepositoriesModule = module {
    single<HomeFilmRepository> {
        HomeFilmRepositoryImpl(
            remoteDataSource = get()
        )
    }
}
