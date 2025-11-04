package com.genres.data.repository

import com.genres.data.datasource.GenresDataSource
import com.genres.domain.entities.GenreEntity
import com.genres.domain.repository.GenresRepository
import com.shared.domain.entities.FilmEntity

class GenresRepositoryImpl(
    private val remoteDataSource: GenresDataSource
) : GenresRepository {

    override suspend fun getGenresFilms(): List<GenreEntity> =
        remoteDataSource.getGenres()

    override suspend fun getFilmsByGenre(id: Int): List<FilmEntity> =
        remoteDataSource.getFilmsByGenre(id)
}
