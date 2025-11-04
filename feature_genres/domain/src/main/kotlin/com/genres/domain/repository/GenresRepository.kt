package com.genres.domain.repository

import com.shared.domain.entities.FilmEntity
import com.genres.domain.entities.GenreEntity

interface GenresRepository {

    suspend fun getGenresFilms(): List<GenreEntity>
    suspend fun getFilmsByGenre(id: Int): List<FilmEntity>
}
