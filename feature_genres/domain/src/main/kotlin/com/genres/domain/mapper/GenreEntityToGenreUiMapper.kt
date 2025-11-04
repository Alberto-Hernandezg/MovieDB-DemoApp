package com.genres.domain.mapper

import com.genres.domain.entities.GenreEntity
import com.genres.domain.model.Genre

object GenreEntityToGenreUiMapper {
    fun map(entity: GenreEntity): Genre {
        return Genre(id = entity.id, name = entity.name)
    }
}
