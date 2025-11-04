package com.shared.domain.mapper

import com.shared.domain.entities.FilmEntity
import com.shared.domain.model.Film

object FilmEntityToFilmMapper {
    fun map(entity: FilmEntity): Film {
        return Film(
            id = entity.id,
            title = entity.title,
            rating = entity.rating
        )
    }
}