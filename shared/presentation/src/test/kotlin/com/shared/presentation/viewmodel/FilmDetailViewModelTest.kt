package com.shared.presentation.viewmodel

import com.shared.domain.model.FilmDetails
import com.shared.domain.usecases.GetFilmDetailsUseCase
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

@OptIn(ExperimentalCoroutinesApi::class)
class FilmDetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getFilmDetailsUseCase: GetFilmDetailsUseCase
    private lateinit var viewModel: FilmDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getFilmDetailsUseCase = mockk()
        viewModel = FilmDetailViewModel(getFilmDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadFilmDetails updates state with film on success`() = runTest {
        val filmId = 1
        val fakeFilm = mockk<FilmDetails>()
        coEvery { getFilmDetailsUseCase(filmId) } returns fakeFilm

        viewModel.loadFilmDetails(filmId)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.first()
        assertEquals(false, state.isLoading)
        assertEquals(false, state.isError)
        assertEquals(fakeFilm, state.film)
    }
}
