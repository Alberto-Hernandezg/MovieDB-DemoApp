package com.pruebatecnica.di.modules

import com.pruebatecnica.di.modules.genres.genresDataSourcesModule
import com.pruebatecnica.di.modules.genres.genresPresentationModule
import com.pruebatecnica.di.modules.genres.genresRepositoriesModule
import com.pruebatecnica.di.modules.genres.genresServicesModule
import com.pruebatecnica.di.modules.genres.genresUseCasesModule
import org.koin.dsl.module

val genresModule = module {
    includes(
        genresPresentationModule,
        genresUseCasesModule,
        genresRepositoriesModule,
        genresDataSourcesModule,
        genresServicesModule
    )
}
