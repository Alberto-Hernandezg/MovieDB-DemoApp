package com.home.presentation.state

import com.shared.domain.model.Film

data class HomeViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val films: List<Film> = emptyList()
)
