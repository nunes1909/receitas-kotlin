package com.example.receitas

import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.data.repository.RepositoryDataSource
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitas
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase
import com.example.receitas.domain.useCase.criaReceita.CriaReceita
import com.example.receitas.domain.useCase.criaReceita.CriaReceitaUseCase
import com.example.receitas.presenter.viewmodel.FormularioReceitaViewModel
import com.example.receitas.presenter.viewmodel.ListaReceitasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    val modules = listOf(
        dataModule(),
        domainModule(),
        listaReceitasViewModel(),
        formularioReceitaViewModel()
    )

    private fun dataModule(): Module {
        return module {
            factory { get<ReceitaDatabase>().receitaDao() }
            factory { get<ReceitaDatabase>().tipoReceitaDao() }
            factory { get<ReceitaDatabase>().nivelReceitaDao() }
            factory { ReceitaDatabase.getInstance(get()) }
            factory { RepositoryDataSource(get()) }
        }
    }

    private fun domainModule(): Module {
        return module {
            factory<CriaReceitaUseCase> { CriaReceita(get()) }
            factory<BuscaTodasReceitasUseCase> { BuscaTodasReceitas(get()) }
        }
    }

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
}