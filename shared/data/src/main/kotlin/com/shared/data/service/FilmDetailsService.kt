package com.shared.data.service

import com.shared.data.dto.FilmDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmDetailsService {

    @GET("movie/{id}")
    suspend fun getFilmDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Response<FilmDetailsDto>
}
