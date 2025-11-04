package com.shared.domain.repository

import com.shared.domain.entities.FilmDetailsEntity

interface FilmDetailsRepository {
    suspend fun getFilmDetails(id: Int): FilmDetailsEntity
}
