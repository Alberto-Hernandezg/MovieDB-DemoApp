package com.pruebatecnica.di.modules

import com.pruebatecnica.di.modules.search.searchDataSourcesModule
import com.pruebatecnica.di.modules.search.searchPresentationModule
import com.pruebatecnica.di.modules.search.searchRepositoriesModule
import com.pruebatecnica.di.modules.search.searchServicesModule
import com.pruebatecnica.di.modules.search.searchUseCasesModule
import org.koin.dsl.module

val searchModule = module {
    includes(
        searchPresentationModule,
        searchUseCasesModule,
        searchRepositoriesModule,
        searchDataSourcesModule,
        searchServicesModule
    )
}
