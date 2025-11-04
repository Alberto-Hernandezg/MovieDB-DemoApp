package com.search.data.datasource

import com.shared.domain.entities.FilmEntity

interface FilmSearchDataSource {

    suspend fun getSearchFilms(title: String): List<FilmEntity>
}
