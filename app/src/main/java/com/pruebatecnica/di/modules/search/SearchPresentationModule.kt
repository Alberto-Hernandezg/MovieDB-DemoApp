package com.pruebatecnica.di.modules.search

import com.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchPresentationModule = module {
    viewModel {
        SearchViewModel(
            searchFilmsUseCase = get()
        )
    }
}
