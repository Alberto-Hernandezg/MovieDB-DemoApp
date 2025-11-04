package com.shared.data.datasource

import com.shared.data.mapper.FilmDetailsDtoToFilmDetailsEntityMapper
import com.shared.data.service.FilmDetailsService
import com.shared.domain.entities.FilmDetailsEntity

class FilmDetailsRemoteDataSource(
    private val service: FilmDetailsService,
    private val apiKey: String
) : FilmDetailsDataSource {

    override suspend fun getFilmDetails(id: Int): FilmDetailsEntity =
        service.getFilmDetails(id, apiKey).body()?.let { FilmDetailsDtoToFilmDetailsEntityMapper.map(it) } ?: FilmDetailsEntity()
}
