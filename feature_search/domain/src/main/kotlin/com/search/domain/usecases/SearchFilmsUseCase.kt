package com.search.domain.usecases

import com.search.domain.repository.FilmSearchRepository
import com.shared.domain.mapper.FilmEntityToFilmMapper
import com.shared.domain.model.Film

class SearchFilmsUseCase(private val repository: FilmSearchRepository) {

    suspend operator fun invoke(title: String): List<Film> =
        repository.getSearchFilms(title).map { FilmEntityToFilmMapper.map(it) }
}

