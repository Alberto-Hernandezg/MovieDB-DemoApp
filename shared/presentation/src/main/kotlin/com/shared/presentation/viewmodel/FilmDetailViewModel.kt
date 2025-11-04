package com.shared.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shared.domain.usecases.GetFilmDetailsUseCase
import com.shared.presentation.state.FilmDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<FilmDetailState> = MutableStateFlow(FilmDetailState())
    val state: StateFlow<FilmDetailState>
        get() = _state.asStateFlow()

    fun loadFilmDetails(filmId: Int) {
        _state.value = FilmDetailState(isLoading = true)
        viewModelScope.launch {
            runCatching {
                getFilmDetailsUseCase(filmId)
            }.onSuccess { film ->
                _state.update { it.copy(film = film, isLoading = false, isError = false) }
            }
        }
    }
}

