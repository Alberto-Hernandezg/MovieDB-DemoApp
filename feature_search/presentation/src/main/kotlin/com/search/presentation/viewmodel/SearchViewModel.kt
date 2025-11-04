package com.search.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.search.domain.usecases.SearchFilmsUseCase
import com.search.presentation.state.SearchViewState
import com.shared.presentation.view.FilmDetailActivity
import com.shared.presentation.view.FilmDetailActivity.Companion.EXTRA_FILM_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchFilmsUseCase: SearchFilmsUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<SearchViewState> = MutableStateFlow(SearchViewState())
    val state: StateFlow<SearchViewState>
        get() = _state.asStateFlow()

    fun searchFilms(title: String) {
        _state.update { it.copy(isLoading = true, isError = false, query = title) }
        viewModelScope.launch {
            runCatching {
                searchFilmsUseCase(title)
            }.onSuccess { films ->
                _state.update { it.copy(films = films, isLoading = false, isError = false) }
            }.onFailure {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun goBackToGenres() {
        _state.update { it.copy(query = "", films = emptyList()) }
    }

    fun goToDetail(filmId: Int, context: Context): Intent {
        val intent = Intent(context, FilmDetailActivity::class.java)
        intent.putExtra(EXTRA_FILM_ID, filmId)
        return intent
    }
}

