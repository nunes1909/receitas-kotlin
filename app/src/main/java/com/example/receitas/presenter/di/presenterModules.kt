package com.example.receitas.presenter.di

import com.example.receitas.presenter.mapper.PresenterMapper
import com.example.receitas.presenter.mapper.ReceitaMapper
import com.example.receitas.presenter.ui.viewmodel.FormularioReceitaViewModel
import com.example.receitas.presenter.ui.viewmodel.ListaReceitasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listaReceitasViewModelModules = module {
    viewModel {
        ListaReceitasViewModel(
            get(),
            get(),
            get()
        )
    }
}

val formularioReceitasViewModelModules = module {
    factory { ReceitaMapper(get(), get()) }
    factory { PresenterMapper(get()) }

    viewModel {
        FormularioReceitaViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}

val presenterModules = listOf(
    listaReceitasViewModelModules,
    formularioReceitasViewModelModules
)