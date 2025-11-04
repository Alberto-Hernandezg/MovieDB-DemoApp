package com.shared.data.datasource

import com.shared.domain.entities.FilmDetailsEntity

interface FilmDetailsDataSource {

    suspend fun getFilmDetails(id: Int): FilmDetailsEntity
}
