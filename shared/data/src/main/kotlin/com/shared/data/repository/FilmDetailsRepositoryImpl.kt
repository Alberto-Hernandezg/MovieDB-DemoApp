package com.shared.data.repository

import com.shared.data.datasource.FilmDetailsDataSource
import com.shared.domain.entities.FilmDetailsEntity
import com.shared.domain.repository.FilmDetailsRepository

class FilmDetailsRepositoryImpl(
    private val remoteDataSource: FilmDetailsDataSource
) : FilmDetailsRepository {

    override suspend fun getFilmDetails(id: Int): FilmDetailsEntity =
        remoteDataSource.getFilmDetails(id)
}
