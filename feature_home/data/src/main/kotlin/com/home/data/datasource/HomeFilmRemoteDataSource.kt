package com.home.data.datasource

import com.home.data.service.HomeFilmsService
import com.shared.data.mapper.FilmDtoToFilmEntityMapper
import com.shared.domain.entities.FilmEntity

class HomeFilmRemoteDataSource(
    private val service: HomeFilmsService,
    private val apiKey: String
) : HomeFilmDataSource {

    override suspend fun getHomeFilms(): List<FilmEntity> =
        service.getHomeFilms(apiKey).body()?.films?.map { FilmDtoToFilmEntityMapper.map(it) }.orEmpty()
}
