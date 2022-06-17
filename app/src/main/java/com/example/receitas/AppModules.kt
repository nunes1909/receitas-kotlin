package com.example.receitas

import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.data.repository.formulario.FormularioRepository
import com.example.receitas.data.repository.receitas.ReceitasRepository
import com.example.receitas.domain.useCase.buscaReceita.BuscaReceitaPorId
import com.example.receitas.domain.useCase.buscaReceita.BuscaReceitaPorIdUseCase
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitas
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase
import com.example.receitas.domain.useCase.buscaTipoNivel.BuscaDescricaoPeloId
import com.example.receitas.domain.useCase.buscaTipoNivel.BuscaDescricaoPeloIdUseCase
import com.example.receitas.domain.useCase.buscaTipoNivel.BuscaIdPelaDescricao
import com.example.receitas.domain.useCase.buscaTipoNivel.BuscaIdPelaDescricaoUseCase
import com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario.BuscaTodosNiveis
import com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario.BuscaTodosNiveisUseCase
import com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario.BuscaTodosTipos
import com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario.BuscaTodosTiposUseCase
import com.example.receitas.domain.useCase.criaReceita.SalvaReceita
import com.example.receitas.domain.useCase.criaReceita.SalvaReceitaUseCase
import com.example.receitas.domain.useCase.deletaReceita.DeletaReceita
import com.example.receitas.domain.useCase.deletaReceita.DeletaReceitaUseCase
import com.example.receitas.domain.useCase.deletaReceita.DeletaTodasReceitas
import com.example.receitas.domain.useCase.deletaReceita.DeletaTodasReceitasUseCase
import com.example.receitas.presenter.mapper.ReceitaMapper
import com.example.receitas.presenter.ui.viewmodel.FormularioReceitaViewModel
import com.example.receitas.presenter.ui.viewmodel.ListaReceitasViewModel
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
            factory { ReceitasRepository(get()) }
            factory { FormularioRepository(get(), get()) }
        }
    }

    private fun domainModule(): Module {
        return module {
            factory<SalvaReceitaUseCase> { SalvaReceita(get()) }
            factory<BuscaTodasReceitasUseCase> { BuscaTodasReceitas(get()) }
            factory<BuscaReceitaPorIdUseCase> { BuscaReceitaPorId(get()) }
            factory<BuscaTodosTiposUseCase> { BuscaTodosTipos(get()) }
            factory<BuscaTodosNiveisUseCase> { BuscaTodosNiveis(get()) }
            factory<DeletaReceitaUseCase> { DeletaReceita(get()) }
            factory<DeletaTodasReceitasUseCase> { DeletaTodasReceitas(get()) }
            factory<BuscaIdPelaDescricaoUseCase> { BuscaIdPelaDescricao(get()) }
            factory<BuscaDescricaoPeloIdUseCase> { BuscaDescricaoPeloId(get()) }
        }
    }

    private fun listaReceitasViewModel(): Module {
        return module {
            viewModel {
                ListaReceitasViewModel(
                    get(),
                    get()
                )
            }
        }
    }

    private fun formularioReceitaViewModel(): Module {
        return module {
            factory { ReceitaMapper(get(), get()) }
            viewModel {
                FormularioReceitaViewModel(
                    get(),
                    get(),
                    get(),
                    get(),
                    get(),
                    get()
                )
            }
        }
    }
}