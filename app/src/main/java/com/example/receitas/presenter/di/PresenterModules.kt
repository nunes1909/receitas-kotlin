package com.example.receitas.presenter.di

import com.example.receitas.presenter.viewmodel.FormularioReceitaViewModel
import com.example.receitas.presenter.viewmodel.ListaReceitasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val presenterModule = arrayListOf(
    listaReceitasViewModel(),
    formularioReceitaViewModel()
)

private fun listaReceitasViewModel(): Module {
    return module {
        viewModel { ListaReceitasViewModel(get()) }
    }
}

private fun formularioReceitaViewModel(): Module {
    return module {
        viewModel { FormularioReceitaViewModel(get()) }
    }
}
