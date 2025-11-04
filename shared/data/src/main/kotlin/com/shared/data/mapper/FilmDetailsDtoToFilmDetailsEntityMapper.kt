package com.shared.data.mapper

import com.shared.data.dto.FilmDetailsDto
import com.shared.domain.entities.FilmDetailsEntity

object FilmDetailsDtoToFilmDetailsEntityMapper {
    fun map(dto: FilmDetailsDto): FilmDetailsEntity {
        return FilmDetailsEntity(
            id = dto.id,
            title = dto.title,
            rating = dto.rating,
            releaseDate = dto.releaseDate,
            overview = dto.overview,
            revenue = dto.revenue,
            spokenLanguages = dto.spokenLanguages.map { it.name },
            status = dto.status,
            genres = dto.genres.map { it.name },
            imagePath = dto.imagePath
        )
    }
}
