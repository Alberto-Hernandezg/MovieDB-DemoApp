package com.pruebatecnica.di.modules.genres

import com.genres.data.repository.GenresRepositoryImpl
import com.genres.domain.repository.GenresRepository
import org.koin.dsl.module

val genresRepositoriesModule = module {
    single<GenresRepository> {
        GenresRepositoryImpl(
            remoteDataSource = get()
        )
    }
}
