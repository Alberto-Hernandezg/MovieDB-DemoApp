package com.pruebatecnica.di.modules

import com.pruebatecnica.di.modules.filmdetail.filmDetailsDataSourcesModule
import com.pruebatecnica.di.modules.filmdetail.filmDetailsPresentationModule
import com.pruebatecnica.di.modules.filmdetail.filmDetailsRepositoriesModule
import com.pruebatecnica.di.modules.filmdetail.filmDetailsServicesModule
import com.pruebatecnica.di.modules.filmdetail.filmDetailsUseCasesModule
import org.koin.dsl.module

val filmDetailModule = module {
    includes(
        filmDetailsPresentationModule,
        filmDetailsServicesModule,
        filmDetailsRepositoriesModule,
        filmDetailsUseCasesModule,
        filmDetailsDataSourcesModule
    )
}
