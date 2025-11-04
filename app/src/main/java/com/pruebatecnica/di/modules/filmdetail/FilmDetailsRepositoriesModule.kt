package com.pruebatecnica.di.modules.filmdetail

import com.shared.data.repository.FilmDetailsRepositoryImpl
import com.shared.domain.repository.FilmDetailsRepository
import org.koin.dsl.module

val filmDetailsRepositoriesModule = module {
    single<FilmDetailsRepository> {
        FilmDetailsRepositoryImpl(
            remoteDataSource = get()
        )
    }
}
