package com.shared.domain.mapper

import com.shared.domain.entities.FilmDetailsEntity
import com.shared.domain.model.FilmDetails

object FilmDetailsEntityToFilmDetailsMapper {
    fun map(entity: FilmDetailsEntity): FilmDetails {
        return FilmDetails(
            id = entity.id,
            title = entity.title,
            rating = entity.rating,
            releaseDate = entity.releaseDate,
            overview = entity.overview,
            revenue = entity.revenue,
            spokenLanguages = entity.spokenLanguages,
            status = entity.status,
            genres = entity.genres,
            imagePath = entity.imagePath
        )
    }
}