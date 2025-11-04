package com.home.domain.repository

import com.shared.domain.entities.FilmEntity

interface HomeFilmRepository {

    suspend fun getHomeFilms(): List<FilmEntity>
}
