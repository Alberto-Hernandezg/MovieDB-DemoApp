package com.home.presentation.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.home.presentation.R
import com.home.presentation.compose.components.HomeFilm
import com.home.presentation.viewmodel.HomeViewModel
import com.shared.domain.model.Film

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onRetry: () -> Unit,
    onFilmClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold {
        Surface(
            modifier = Modifier
                .nestedScroll(rememberNestedScrollInteropConnection())
                .padding(it)
        ) {
            when {
                state.isLoading -> {
                    HomeLoading()
                }

                state.isError -> {
                    HomeError(
                        onRetry = onRetry
                    )
                }

                else -> {
                    HomeContent(
                        state.films,
                        onFilmClick,
                        onFavoriteClick
                    )
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    films: List<Film>,
    onFilmClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    Column(Modifier
        .padding(12.dp)
        .verticalScroll(rememberScrollState())
    ) {
        repeat(films.size) {
            Column {
                HomeFilm(
                    filmData = films[it],
                    onClick = { filmId ->
                        onFilmClick(filmId)
                    },
                    onFavoriteClick = { filmId ->
                        onFavoriteClick(filmId)
                    }
                )
                if (it < films.size - 1) {
                    Spacer(Modifier.fillMaxWidth().height(1.dp).background(color = Color.Black))
                }
            }
        }
    }
}

@Composable
fun HomeLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.film_home_loading),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HomeError(
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Button(
            onClick = onRetry
        ) {
            Text(
                text = stringResource(R.string.film_home_retry)
            )
        }
    }
}
