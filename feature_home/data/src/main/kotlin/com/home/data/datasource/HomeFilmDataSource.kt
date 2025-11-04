package com.home.data.datasource

import com.shared.domain.entities.FilmEntity

interface HomeFilmDataSource {

    suspend fun getHomeFilms(): List<FilmEntity>
}
