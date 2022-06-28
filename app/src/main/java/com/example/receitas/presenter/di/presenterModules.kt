package com.example.receitas.presenter.di

import com.example.receitas.domain.useCase.buscaReceita.BuscaReceitaPorId
import com.example.receitas.domain.useCase.buscaReceita.BuscaReceitaPorIdUseCase
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitas
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase
import com.example.receitas.domain.useCase.deletaReceita.DeletaTodasReceitas
import com.example.receitas.domain.useCase.deletaReceita.DeletaTodasReceitasUseCase
import com.example.receitas.presenter.mapper.ReceitaMapper
import com.example.receitas.presenter.ui.viewmodel.FormularioReceitaViewModel
import com.example.receitas.presenter.ui.viewmodel.ListaReceitasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listaReceitasViewModelModules = module {
    viewModel {
        ListaReceitasViewModel(
            get(),
            get()
        )
    }
}

val formularioReceitasViewModelModules = module {

    factory { ReceitaMapper(get(), get()) }

    viewModel {
        FormularioReceitaViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }
}

val presenterModules = listOf(listaReceitasViewModelModules, formularioReceitasViewModelModules)