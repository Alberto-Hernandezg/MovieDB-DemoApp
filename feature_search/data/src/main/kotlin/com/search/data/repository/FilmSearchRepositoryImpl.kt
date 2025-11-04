package com.search.data.repository

import com.search.data.datasource.FilmSearchDataSource
import com.search.domain.repository.FilmSearchRepository
import com.shared.domain.entities.FilmEntity

class FilmSearchRepositoryImpl(
    private val remoteDataSource: FilmSearchDataSource
) : FilmSearchRepository {

    override suspend fun getSearchFilms(title: String): List<FilmEntity> =
        remoteDataSource.getSearchFilms(title)
}
