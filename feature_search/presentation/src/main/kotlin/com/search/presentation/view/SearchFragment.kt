package com.search.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.search.presentation.compose.screens.InputSearchScreen
import com.search.presentation.databinding.FragmentSearchBinding
import com.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var viewBinding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding.composeView) {
            setContent {
                InputSearchScreen(
                    viewModel = viewModel,
                    onSearchClick = { query ->
                        viewModel.searchFilms(query)
                    },
                    onRetry = {
                        viewModel.searchFilms(viewModel.state.value.query)
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
