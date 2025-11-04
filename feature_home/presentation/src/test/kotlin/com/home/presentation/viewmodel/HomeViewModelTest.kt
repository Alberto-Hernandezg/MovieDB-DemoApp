package com.home.presentation.viewmodel

import android.content.Context
import com.home.domain.usecases.GetHomeFilmListUseCase
import com.home.presentation.state.HomeViewState
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
import android.content.Intent

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getHomeFilmListUseCase: GetHomeFilmListUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getHomeFilmListUseCase = mockk()
        viewModel = HomeViewModel(getHomeFilmListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getHomeFilmList updates state with films on success`() = runTest {
        val fakeFilms = listOf(Film(1, "title", 0.0, false))
        coEvery { getHomeFilmListUseCase() } returns fakeFilms

        viewModel.getHomeFilmList()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(false, state.isError)
        assertEquals(fakeFilms, state.films)
    }

    @Test
    fun `getHomeFilmList updates state with error on failure`() = runTest {
        coEvery { getHomeFilmListUseCase() } throws RuntimeException("Error")

        viewModel.getHomeFilmList()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(true, state.isError)
        assertEquals(emptyList<Film>(), state.films)
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
