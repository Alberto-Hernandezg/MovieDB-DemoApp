package com.genres.data.datasource

import com.genres.domain.entities.GenreEntity
import com.shared.domain.entities.FilmEntity

interface GenresDataSource {

    suspend fun getGenres(): List<GenreEntity>
    suspend fun getFilmsByGenre(id: Int): List<FilmEntity>
}
