package com.home.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.domain.usecases.GetHomeFilmListUseCase
import com.home.presentation.state.HomeViewState
import com.shared.presentation.view.FilmDetailActivity
import com.shared.presentation.view.FilmDetailActivity.Companion.EXTRA_FILM_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomeFilmListUseCase: GetHomeFilmListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state.asStateFlow()

    init {
        loadHome()
    }

    fun loadHome() {
        getHomeFilmList()
    }

    fun getHomeFilmList() {
        _state.update { it.copy(isLoading = true, isError = false) }
        viewModelScope.launch {
            runCatching {
                getHomeFilmListUseCase()
            }.onSuccess { films ->
                _state.update { it.copy(films = films, isLoading = false, isError = false) }
            }.onFailure {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun toggleFavorite(filmId: Int) {
        _state.update { state ->
            state.copy(films = state.films.map { film ->
                if (film.id == filmId) {
                    film.copy(isFav = !film.isFav)
                } else {
                    film
                }
            })
        }
    }

    fun goToDetail(filmId: Int, context: Context): Intent {
        val intent = Intent(context, FilmDetailActivity::class.java)
        intent.putExtra(EXTRA_FILM_ID, filmId)
        return intent
    }
}

