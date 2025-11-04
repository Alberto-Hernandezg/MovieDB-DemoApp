package com.genres.data.mapper

import com.genres.data.dto.GenreDto
import com.genres.domain.entities.GenreEntity

object GenresDtoToGenresEntityMapper {
    fun map(dto: GenreDto): GenreEntity {
        return GenreEntity(name = dto.name, id = dto.id)
    }
}
