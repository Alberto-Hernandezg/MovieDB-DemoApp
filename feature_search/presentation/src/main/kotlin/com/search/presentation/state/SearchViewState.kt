package com.search.presentation.state

import com.shared.domain.model.Film

data class SearchViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val query: String = "",
    val films: List<Film> = emptyList()
)
