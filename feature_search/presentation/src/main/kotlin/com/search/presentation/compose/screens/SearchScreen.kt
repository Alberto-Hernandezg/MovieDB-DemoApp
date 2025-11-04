package com.search.presentation.compose.screens

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.search.presentation.R
import com.search.presentation.compose.components.FilmSearchItem
import com.search.presentation.viewmodel.SearchViewModel
import com.shared.domain.model.Film

@Composable
fun InputSearchScreen(
    viewModel: SearchViewModel,
    onRetry: () -> Unit,
    onFilmClick: (Int) -> Unit,
    onSearchClick: (String) -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val inputText = remember { mutableStateOf("") }
    Scaffold {
        Surface(
            modifier = Modifier
                .nestedScroll(rememberNestedScrollInteropConnection())
                .padding(it)
        ) {
            when {
                state.isLoading -> {
                    SearchLoading(text = stringResource(R.string.film_search_loading_films))
                }

                state.isError -> {
                    SearchError(onRetry = onRetry)
                }

                else -> {
                    if (state.query.isEmpty()) {
                        SearchScreen(
                            onSearchClick = { query ->
                                onSearchClick(query)
                            },
                            onInputTextChanged = { text ->
                                inputText.value = text
                            },
                            inputText = inputText.value
                        )
                    } else {
                        SearchContent(
                            films = state.films,
                            query = state.query,
                            onFilmClick = onFilmClick,
                            onBack = onBack
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchScreen(
    onInputTextChanged: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    inputText: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = inputText,
            onValueChange = onInputTextChanged,
            label = { Text(stringResource(R.string.film_search_label)) },
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_saved_search_24),
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            if (inputText.isNotBlank()) {
                                onSearchClick(inputText)
                            }
                        }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    films : List<Film>,
    query: String,
    onFilmClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.film_search_title, query),
                        style = MaterialTheme.typography.headlineSmall,
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
        Column(Modifier
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
        ) {
            if (films.isEmpty()) {
                EmptyScreen()
            } else {
                repeat(films.size) {
                    Column {
                        FilmSearchItem (
                            filmData = films[it],
                            onClick = { filmId ->
                                onFilmClick(filmId)
                            }
                        )
                        if (it < films.size - 1) {
                            Spacer(Modifier.fillMaxWidth().height(1.dp).background(color = Color.Black))
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
            text = stringResource(R.string.film_search_no_films),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SearchLoading(
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
fun SearchError(
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
                text = stringResource(R.string.film_search_retry)
            )
        }
    }
}
