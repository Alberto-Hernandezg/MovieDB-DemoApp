package com.genres.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.genres.domain.model.Genre
import com.genres.presentation.compose.screens.GenresScreen
import com.genres.presentation.databinding.FragmentGenresBinding
import com.genres.presentation.viewmodel.GenresViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenresFragment : Fragment() {

    private lateinit var viewBinding: FragmentGenresBinding

    private val viewModel: GenresViewModel by viewModel()

    private lateinit var selectedGenre: Genre

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentGenresBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding.composeView) {
            setContent {
                GenresScreen(
                    viewModel = viewModel,
                    onRetryGenders = viewModel::getGenresList,
                    onGenreClick = { genre ->
                        selectedGenre = genre
                        viewModel.getFilmsByGenre(genre)
                    },
                    onRetryFilms = {
                        viewModel.getFilmsByGenre(selectedGenre)
                    },
                    onBack = viewModel::goBackToGenres,
                    onFilmClick = { filmId ->
                        startActivity(viewModel.goToDetail(filmId, context))
                    }
                )
            }
        }
    }
}
