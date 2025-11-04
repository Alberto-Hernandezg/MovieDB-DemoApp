package com.shared.domain.usecases

import com.shared.domain.mapper.FilmDetailsEntityToFilmDetailsMapper
import com.shared.domain.model.FilmDetails
import com.shared.domain.repository.FilmDetailsRepository

class GetFilmDetailsUseCase(private val repository: FilmDetailsRepository) {
    suspend operator fun invoke(id: Int): FilmDetails =
        FilmDetailsEntityToFilmDetailsMapper.map(repository.getFilmDetails(id))}
