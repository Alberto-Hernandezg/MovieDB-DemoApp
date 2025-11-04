package com.home.data.repository

import com.home.data.datasource.HomeFilmDataSource
import com.home.domain.repository.HomeFilmRepository
import com.shared.domain.entities.FilmEntity

class HomeFilmRepositoryImpl(
    private val remoteDataSource: HomeFilmDataSource
) : HomeFilmRepository {

    override suspend fun getHomeFilms(): List<FilmEntity> =
        remoteDataSource.getHomeFilms()
}
