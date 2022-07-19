package com.example.receitas.domain.di

import com.example.receitas.data.formulario.repository.FormularioRepositoryImpl
import com.example.receitas.data.receita.repository.ReceitasRepositoryImpl
import com.example.receitas.domain.repository.FormularioRepository
import com.example.receitas.domain.repository.ReceitasRepository
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
import org.koin.dsl.module

val domainModules = module {
    factory<SalvaReceitaUseCase> { SalvaReceita( get() ) }

    factory<BuscaTodasReceitasUseCase> { BuscaTodasReceitas( get() ) }
    factory<BuscaReceitaPorIdUseCase> { BuscaReceitaPorId( get() ) }

    factory<BuscaTodosTiposUseCase> { BuscaTodosTipos( get() ) }
    factory<BuscaTodosNiveisUseCase> { BuscaTodosNiveis( get() ) }

    factory<DeletaReceitaUseCase> { DeletaReceita( get() ) }
    factory<DeletaTodasReceitasUseCase> { DeletaTodasReceitas( get() ) }

    factory<BuscaDescricaoPeloIdUseCase> { BuscaDescricaoPeloId( get() ) }
    factory<BuscaIdPelaDescricaoUseCase> { BuscaIdPelaDescricao( get() ) }

    factory<ReceitasRepository> { ReceitasRepositoryImpl(get(), get()) }
    factory<FormularioRepository> { FormularioRepositoryImpl(get(), get(), get()) }
}