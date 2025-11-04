package com.search.domain.repository

import com.shared.domain.entities.FilmEntity

interface FilmSearchRepository {
    suspend fun getSearchFilms(title: String): List<FilmEntity>
}
