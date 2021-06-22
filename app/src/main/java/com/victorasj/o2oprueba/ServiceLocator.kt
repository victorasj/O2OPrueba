package com.victorasj.o2oprueba

import android.app.Application
import com.victorasj.data.repository.BeersRepository
import com.victorasj.data.source.RemoteDataSource
import com.victorasj.interactor.GetBeerById
import com.victorasj.interactor.GetBeers
import com.victorasj.interactor.GetBeersFiltered
import com.victorasj.o2oprueba.server.BeerDataSource
import com.victorasj.o2oprueba.ui.beer.BeerFragment
import com.victorasj.o2oprueba.ui.beer.BeerViewModel
import com.victorasj.o2oprueba.ui.main.MainFragment
import com.victorasj.o2oprueba.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initServiceLocator(){
    startKoin {
        androidLogger()
        androidContext(this@initServiceLocator)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    factory<RemoteDataSource> { BeerDataSource() }
}

private val dataModule = module {
    factory { BeersRepository(get()) }
}

private val scopesModule = module {
    scope(named<MainFragment>()){
        viewModel { MainViewModel(get(), get()) }
        scoped { GetBeers(get())  }
        scoped { GetBeersFiltered(get())  }
    }

    scope(named<BeerFragment>()){
        viewModel { (id : Long) -> BeerViewModel(get(), id) }
        scoped { GetBeerById(get())  }
    }
}