package com.pruebatecnica.di.modules

import com.pruebatecnica.di.modules.home.homeDataSourcesModule
import com.pruebatecnica.di.modules.home.homePresentationModule
import com.pruebatecnica.di.modules.home.homeRepositoriesModule
import com.pruebatecnica.di.modules.home.homeServicesModule
import com.pruebatecnica.di.modules.home.homeUseCasesModule
import org.koin.dsl.module

val homeModule = module {
    includes(
        homePresentationModule,
        homeUseCasesModule,
        homeRepositoriesModule,
        homeDataSourcesModule,
        homeServicesModule
    )
}
