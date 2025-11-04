package com.pruebatecnica.di.modules.home

import com.home.data.service.HomeFilmsService
import com.pruebatecnica.managers.BASE_URL
import com.pruebatecnica.managers.provideOkHttp
import com.pruebatecnica.managers.provideRetrofit
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val HOME_FILMS = named("HomeFilms")

val homeServicesModule = module {

    single {
        get<Retrofit>(HOME_FILMS).create(HomeFilmsService::class.java)
    }

    single(HOME_FILMS) {
        provideRetrofit(
            baseUrl = BASE_URL,
            provideOkHttp().build()
        )
    }
}
