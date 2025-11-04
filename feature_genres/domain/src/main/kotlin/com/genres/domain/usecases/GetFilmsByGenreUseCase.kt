package com.genres.domain.usecases

import com.shared.domain.mapper.FilmEntityToFilmMapper
import com.shared.domain.model.Film
import com.genres.domain.repository.GenresRepository

class GetFilmsByGenreUseCase(private val repository: GenresRepository) {

    suspend operator fun invoke(id: Int): List<Film> =
        repository.getFilmsByGenre(id).map { FilmEntityToFilmMapper.map(it) }
}

