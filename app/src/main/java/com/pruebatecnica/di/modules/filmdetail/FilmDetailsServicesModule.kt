package com.pruebatecnica.di.modules.filmdetail

import com.pruebatecnica.managers.BASE_URL
import com.pruebatecnica.managers.provideOkHttp
import com.pruebatecnica.managers.provideRetrofit
import com.shared.data.service.FilmDetailsService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val FILM_DETAILS = named("film details")

val filmDetailsServicesModule = module {

    single {
        get<Retrofit>(FILM_DETAILS).create(FilmDetailsService::class.java)
    }

    single(FILM_DETAILS) {
        provideRetrofit(
            baseUrl = BASE_URL,
            provideOkHttp().build()
        )
    }
}
