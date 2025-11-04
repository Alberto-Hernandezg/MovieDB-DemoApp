package com.shared.presentation.state

import com.shared.domain.model.FilmDetails

data class FilmDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val film: FilmDetails = FilmDetails()
)
