package com.genres.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genres.domain.model.Genre
import com.genres.domain.usecases.GetFilmsByGenreUseCase
import com.genres.domain.usecases.GetGenreListUseCase
import com.genres.presentation.state.GenresViewState
import com.shared.presentation.view.FilmDetailActivity
import com.shared.presentation.view.FilmDetailActivity.Companion.EXTRA_FILM_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GenresViewModel(
    private val getGenreListUseCase: GetGenreListUseCase,
    private val getFilmsByGenreUseCase: GetFilmsByGenreUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<GenresViewState> = MutableStateFlow(GenresViewState())
    val state: StateFlow<GenresViewState>
        get() = _state.asStateFlow()

    init {
        loadGenres()
    }

    fun loadGenres() {
        getGenresList()
    }

    fun getGenresList() {
        _state.update { it.copy(isLoading = true, isError = false) }
        viewModelScope.launch {
            runCatching {
                getGenreListUseCase()
            }.onSuccess { genres ->
                _state.update { it.copy(genres = genres, isLoading = false, isError = false) }
            }.onFailure {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun getFilmsByGenre(genre: Genre) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            runCatching {
                getFilmsByGenreUseCase(genre.id)
            }.onSuccess { films ->
                _state.update { it.copy(filmsByGenre = films, isLoading = false, isError = false, genreClicked = genre.name) }
            }.onFailure {
                _state.update { it.copy(isLoading = false, isError = true, genreClicked = genre.name) }
            }
        }
    }

    fun goToDetail(filmId: Int, context: Context): Intent {
        val intent = Intent(context, FilmDetailActivity::class.java)
        intent.putExtra(EXTRA_FILM_ID, filmId)
        return intent
    }

    fun goBackToGenres() {
        _state.update { it.copy(genreClicked = "", filmsByGenre = emptyList()) }
    }
}

