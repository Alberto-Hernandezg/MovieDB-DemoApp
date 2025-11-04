package com.shared.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.shared.domain.model.FilmDetails
import com.shared.presentation.R
import com.shared.presentation.viewmodel.FilmDetailViewModel
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreen(
    viewModel: FilmDetailViewModel,
    onRetry: () -> Unit,
    onBackClicked: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = state.film.title,
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
                                onBackClicked()
                            }
                    )
                }
            )
        }
    ) { padding ->
        Surface (
            modifier = Modifier.padding(padding)
        ) {
            if (state.isLoading) {
                DetailsLoading()
            } else if (state.isError) {
                DetailsError(
                    onRetry = onRetry
                )
            } else {
                DetailsContent(
                    state.film
                )
            }
        }
    }
}

@Composable
fun DetailsContent(
    film: FilmDetails
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(height = 400.dp, width = 300.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier.sizeIn(maxHeight = 400.dp, maxWidth = 300.dp),
                model = BASE_IMAGE_URL + film.imagePath,
                contentDescription = "Film Poster",
                placeholder = painterResource(id = R.drawable.baseline_camera_alt_24),
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = film.title,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.film_detail_release_date, film.releaseDate),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.film_detail_overview, film.overview),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.film_detail_revenue, DecimalFormat("#,###")
                .format(film.revenue)
                .replace(",", ".")),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(12.dp))
        val languages = film.spokenLanguages.joinToString(", ") { it }
        Text(
            text = stringResource(R.string.film_detail_languages, languages),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.film_detail_status, film.status),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(12.dp))
        val genres = film.genres.joinToString(", ") { it }
        Text(
            text = stringResource(R.string.film_detail_genres, genres),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun DetailsLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.film_detail_loading),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DetailsError(
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
                text = stringResource(R.string.film_detail_retry)
            )
        }
    }
}

private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original/"
