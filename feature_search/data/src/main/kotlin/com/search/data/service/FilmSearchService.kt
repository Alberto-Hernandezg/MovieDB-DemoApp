package com.search.data.service

import com.shared.data.dto.FilmsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmSearchService {

    @GET("search/movie")
    suspend fun getSearchFilms(@Query("api_key") apiKey: String, @Query("query") title: String): Response<FilmsResponse>
}
