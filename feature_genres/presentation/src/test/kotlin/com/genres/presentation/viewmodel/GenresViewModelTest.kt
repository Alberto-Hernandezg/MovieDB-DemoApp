package com.genres.presentation.viewmodel

import android.content.Context
import android.content.Intent
import com.genres.domain.model.Genre
import com.genres.domain.usecases.GetFilmsByGenreUseCase
import com.genres.domain.usecases.GetGenreListUseCase
import com.genres.presentation.state.GenresViewState
import com.shared.domain.model.Film
import com.shared.presentation.view.FilmDetailActivity.Companion.EXTRA_FILM_ID
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GenresViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getGenreListUseCase: GetGenreListUseCase
    private lateinit var getFilmsByGenreUseCase: GetFilmsByGenreUseCase
    private lateinit var viewModel: GenresViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getGenreListUseCase = mockk()
        getFilmsByGenreUseCase = mockk()
        viewModel = GenresViewModel(getGenreListUseCase, getFilmsByGenreUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getGenresList updates state with genres on success`() = runTest {
        val fakeGenres = listOf(Genre(1, "Action"), Genre(2, "Comedy"))
        coEvery { getGenreListUseCase() } returns fakeGenres

        viewModel.getGenresList()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(false, state.isError)
        assertEquals(fakeGenres, state.genres)
    }

    @Test
    fun `getGenresList updates state with error on failure`() = runTest {
        coEvery { getGenreListUseCase() } throws RuntimeException("Error")

        viewModel.getGenresList()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(true, state.isError)
        assertEquals(emptyList<Genre>(), state.genres)
    }

    @Test
    fun `getFilmsByGenre updates state with films on success`() = runTest {
        val genre = Genre(1, "Action")
        val fakeFilms = listOf(Film(1, "title", 0.0, false))
        coEvery { getFilmsByGenreUseCase(genre.id) } returns fakeFilms

        viewModel.getFilmsByGenre(genre)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(false, state.isError)
        assertEquals(fakeFilms, state.filmsByGenre)
        assertEquals(genre.name, state.genreClicked)
    }

    @Test
    fun `getFilmsByGenre updates state with error on failure`() = runTest {
        val genre = Genre(1, "Action")
        coEvery { getFilmsByGenreUseCase(genre.id) } throws RuntimeException("Error")

        viewModel.getFilmsByGenre(genre)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(true, state.isError)
        assertEquals(emptyList<Film>(), state.filmsByGenre)
        assertEquals(genre.name, state.genreClicked)
    }

    @Test
    fun `goToDetail returns intent with correct extras`() {
        mockkConstructor(Intent::class)
        val context = mockk<Context>(relaxed = true)
        val filmId = 42
        val mockIntent = mockk<Intent>(relaxed = true)

        every { anyConstructed<Intent>().putExtra(EXTRA_FILM_ID, filmId) } returns mockIntent

        val intent = viewModel.goToDetail(filmId, context)

        verify { intent.putExtra(EXTRA_FILM_ID, filmId) }
    }
}
