package com.search.data.datasource

import com.search.data.service.FilmSearchService
import com.shared.data.mapper.FilmDtoToFilmEntityMapper
import com.shared.domain.entities.FilmEntity

class FilmSearchRemoteDataSource(
    private val service: FilmSearchService,
    private val apiKey: String
) : FilmSearchDataSource {

    override suspend fun getSearchFilms(title: String): List<FilmEntity> =
        service.getSearchFilms(apiKey, title).body()?.films?.map { FilmDtoToFilmEntityMapper.map(it) }.orEmpty()
}
