package com.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.home.presentation.databinding.FragmentHomeBinding
import com.home.presentation.compose.screens.HomeScreen
import com.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding.composeView) {
            setContent {
                HomeScreen(
                    viewModel = viewModel,
                    onRetry = viewModel::getHomeFilmList,
                    onFilmClick = { filmId ->
                        startActivity(viewModel.goToDetail(filmId, context))
                    },
                    onFavoriteClick = { filmId ->
                        viewModel.toggleFavorite(filmId)
                    }
                )
            }
        }
    }
}
