package com.home.domain.usecases

import com.home.domain.repository.HomeFilmRepository
import com.shared.domain.mapper.FilmEntityToFilmMapper
import com.shared.domain.model.Film

class GetHomeFilmListUseCase(private val repository: HomeFilmRepository) {

    suspend operator fun invoke(): List<Film> =
        repository.getHomeFilms().map { FilmEntityToFilmMapper.map(it) }
}

