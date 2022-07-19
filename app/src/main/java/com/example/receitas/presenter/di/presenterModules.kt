package com.example.receitas.presenter.di

import com.example.receitas.presenter.formulario.mapper.NivelPresenterMapper
import com.example.receitas.presenter.formulario.mapper.TipoPresenterMapper
import com.example.receitas.presenter.receita.mapper.ReceitaPresenterMapper
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
    factory { ReceitaPresenterMapper(get()) }
    factory { NivelPresenterMapper() }
    factory { TipoPresenterMapper() }

    viewModel {
        FormularioReceitaViewModel(
            get(),
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