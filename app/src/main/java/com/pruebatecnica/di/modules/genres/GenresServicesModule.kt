package com.pruebatecnica.di.modules.genres

import com.genres.data.service.GenresService
import com.pruebatecnica.managers.BASE_URL
import com.pruebatecnica.managers.provideOkHttp
import com.pruebatecnica.managers.provideRetrofit
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val FILMS_BY_GENRE = named("filmsByGenre")

val genresServicesModule = module {

    single {
        get<Retrofit>(FILMS_BY_GENRE).create(GenresService::class.java)
    }

    single(FILMS_BY_GENRE) {
        provideRetrofit(
            baseUrl = BASE_URL,
            provideOkHttp().build()
        )
    }
}
