package com.shared.data.mapper

import com.shared.data.dto.FilmDto
import com.shared.domain.entities.FilmEntity

object FilmDtoToFilmEntityMapper {
    fun map(dto: FilmDto): FilmEntity {
        return FilmEntity(
            id = dto.id,
            title = dto.title,
            rating = dto.rating
        )
    }
}
