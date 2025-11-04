package com.genres.presentation.state

import com.shared.domain.model.Film
import com.genres.domain.model.Genre

data class GenresViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val genreClicked: String = "",
    val filmsByGenre: List<Film> = emptyList()
)
