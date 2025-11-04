package com.shared.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shared.presentation.compose.FilmDetailScreen
import com.shared.presentation.databinding.ActivityFilmDetailBinding
import com.shared.presentation.viewmodel.FilmDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailActivity : AppCompatActivity() {
    private val binding: ActivityFilmDetailBinding by lazy {
        ActivityFilmDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: FilmDetailViewModel by viewModel()

    private val filmId by lazy { intent.getIntExtra(EXTRA_FILM_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.loadFilmDetails(filmId)

        with(binding.composeView) {
            setContent {
                FilmDetailScreen (
                    viewModel = viewModel,
                    onRetry = {
                        viewModel.loadFilmDetails(filmId)
                    },
                    onBackClicked = {
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        const val EXTRA_FILM_ID = "extra_film_id"
    }
}

