package com.home.data.service

import com.shared.data.dto.FilmsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeFilmsService {
    @GET("movie/popular")
    suspend fun getHomeFilms(@Query("api_key") apiKey: String): Response<FilmsResponse>
}
