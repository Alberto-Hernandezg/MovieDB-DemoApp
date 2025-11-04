package com.pruebatecnica.di.modules.search

import com.pruebatecnica.managers.BASE_URL
import com.pruebatecnica.managers.provideOkHttp
import com.pruebatecnica.managers.provideRetrofit
import com.search.data.service.FilmSearchService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val SEARCH = named("search")

val searchServicesModule = module {

    single {
        get<Retrofit>(SEARCH).create(FilmSearchService::class.java)
    }

    single(SEARCH) {
        provideRetrofit(
            baseUrl = BASE_URL,
            provideOkHttp().build()
        )
    }
}
