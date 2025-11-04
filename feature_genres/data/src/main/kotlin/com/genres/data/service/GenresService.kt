package com.genres.data.service

import com.shared.data.dto.FilmsResponse
import com.genres.data.dto.GenresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresService {
    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): Response<GenresResponse>

    @GET("discover/movie")
    suspend fun getFilmsByGenre(@Query("api_key") apiKey: String, @Query("with_genres") id: String): Response<FilmsResponse>
}
