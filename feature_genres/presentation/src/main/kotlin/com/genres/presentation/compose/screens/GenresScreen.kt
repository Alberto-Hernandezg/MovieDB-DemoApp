package com.genres.presentation.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.genres.domain.model.Genre
import com.genres.presentation.R
import com.genres.presentation.compose.components.FilmItem
import com.genres.presentation.compose.components.GenreItem
import com.genres.presentation.viewmodel.GenresViewModel
import com.shared.domain.model.Film

@Composable
fun GenresScreen(
    viewModel: GenresViewModel,
    onRetryGenders: () -> Unit,
    onRetryFilms: () -> Unit,
    onGenreClick: (Genre) -> Unit,
    onFilmClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier
            .nestedScroll(rememberNestedScrollInteropConnection())
    ) {
        when {
            state.isLoading -> {
                GenresLoading(if (state.genreClicked.isEmpty()) stringResource(R.string.film_genres_loading) else stringResource(R.string.film_genres_loading_films))
            }

            state.isError -> {
                GenresError(
                    onRetry = if (state.genreClicked.isEmpty()) onRetryGenders else onRetryFilms
                )
            }

            else -> {
                if (state.genreClicked.isEmpty()) {
                    GenresContent(
                        state.genres,
                        onGenreClick
                    )
                } else {
                    FilmsContent(
                        genre = state.genreClicked,
                        films = state.filmsByGenre,
                        onFilmClick = onFilmClick,
                        onBack = onBack
                    )
                }
            }
        }
    }
}

@Composable
fun GenresContent(
    genres: List<Genre>,
    onGenreClick: (Genre) -> Unit
) {
    Column(Modifier
        .padding(12.dp)
        .verticalScroll(rememberScrollState())
    ) {
        repeat(genres.size) {
            Column {
                GenreItem(
                    genreData = genres[it],
                    onClick = { genre ->
                        onGenreClick(genre)
                    }
                )
                if (it < genres.size - 1) {
                    Spacer(Modifier.fillMaxWidth().height(1.dp).background(color = Color.Black))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsContent(
    genre: String,
    films : List<Film>,
    onFilmClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(R.string.film_genres_title, genre),
                        style = MaterialTheme.typography.headlineSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "Close",
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .clickable {
                                onBack()
                            }
                    )
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (films.isEmpty()) {
                EmptyScreen()
            } else {
                repeat(films.size) {
                    Column {
                        FilmItem(
                            filmData = films[it],
                            onClick = { filmId ->
                                onFilmClick(filmId)
                            }
                        )
                        if (it < films.size - 1) {
                            Spacer(
                                Modifier.fillMaxWidth().height(1.dp).background(color = Color.Black)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.film_genres_no_films),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GenresLoading(
    text: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GenresError(
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onRetry
        ) {
            Text(
                text = stringResource(R.string.film_genres_retry)
            )
        }
    }
}
