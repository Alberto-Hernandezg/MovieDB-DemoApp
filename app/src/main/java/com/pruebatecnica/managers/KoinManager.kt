package com.pruebatecnica.managers

import android.app.Application
import com.pruebatecnica.di.modules.filmDetailModule
import com.pruebatecnica.di.modules.genresModule
import com.pruebatecnica.di.modules.homeModule
import com.pruebatecnica.di.modules.searchModule
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "fdecc886dcb06469c32b050b4ed31948"
const val BASE_URL = "https://api.themoviedb.org/3/"

interface KoinManager : KoinComponent {

    fun Application.initKoin() {
        startKoin {
            androidLogger()
            androidContext(androidContext = this@initKoin)
            modules(
                homeModule,
                genresModule,
                searchModule,
                filmDetailModule
            )
        }
    }
}

fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

fun provideOkHttp() = OkHttpClient.Builder()
    .callTimeout(10000L, TimeUnit.MILLISECONDS)
    .readTimeout(10000L, TimeUnit.MILLISECONDS)
    .retryOnConnectionFailure(true)
