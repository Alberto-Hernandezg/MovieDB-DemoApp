package com.search.presentation.viewmodel

import android.content.Context
import com.search.domain.usecases.SearchFilmsUseCase
import com.search.presentation.state.SearchViewState
import com.shared.presentation.view.FilmDetailActivity
import com.shared.presentation.view.FilmDetailActivity.Companion.EXTRA_FILM_ID
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.verify
import android.content.Intent

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var searchFilmsUseCase: SearchFilmsUseCase
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        searchFilmsUseCase = mockk()
        viewModel = SearchViewModel(searchFilmsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchFilms updates state with films on success`() = runTest {
        val query = "Matrix"
        val fakeFilms = listOf(mockk<com.shared.domain.model.Film>())
        coEvery { searchFilmsUseCase(query) } returns fakeFilms

        viewModel.searchFilms(query)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(false, state.isError)
        assertEquals(fakeFilms, state.films)
        assertEquals(query, state.query)
    }

    @Test
    fun `searchFilms updates state with error on failure`() = runTest {
        val query = "Matrix"
        coEvery { searchFilmsUseCase(query) } throws RuntimeException("Error")

        viewModel.searchFilms(query)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(true, state.isError)
        assertEquals(emptyList<com.shared.domain.model.Film>(), state.films)
        assertEquals(query, state.query)
    }

    @Test
    fun `goBackToGenres resets query and films`() {
        viewModel.goBackToGenres()
        val state = viewModel.state.value
        assertEquals("", state.query)
        assertEquals(emptyList<com.shared.domain.model.Film>(), state.films)
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
