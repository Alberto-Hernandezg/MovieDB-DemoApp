package com.genres.domain.usecases

import com.genres.domain.mapper.GenreEntityToGenreUiMapper
import com.genres.domain.model.Genre
import com.genres.domain.repository.GenresRepository

class GetGenreListUseCase(private val repository: GenresRepository) {

    suspend operator fun invoke(): List<Genre> =
        repository.getGenresFilms().map { GenreEntityToGenreUiMapper.map(it) }
}

