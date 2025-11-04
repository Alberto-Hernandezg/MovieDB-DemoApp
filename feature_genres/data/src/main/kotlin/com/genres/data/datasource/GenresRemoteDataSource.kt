package com.genres.data.datasource

import com.genres.data.mapper.GenresDtoToGenresEntityMapper
import com.genres.data.service.GenresService
import com.genres.domain.entities.GenreEntity
import com.shared.data.mapper.FilmDtoToFilmEntityMapper
import com.shared.domain.entities.FilmEntity

class GenresRemoteDataSource(
    private val service: GenresService,
    private val apiKey: String
) : GenresDataSource {

    override suspend fun getGenres(): List<GenreEntity> =
        service.getGenres(apiKey).body()?.genres?.map { GenresDtoToGenresEntityMapper.map(it) }.orEmpty()

    override suspend fun getFilmsByGenre(id: Int): List<FilmEntity> =
        service.getFilmsByGenre(apiKey, id.toString()).body()?.films?.map { FilmDtoToFilmEntityMapper.map(it) }.orEmpty()
}
