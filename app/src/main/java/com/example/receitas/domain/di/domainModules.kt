package com.example.receitas.domain.di

import com.example.receitas.data.formulario.repository.FormularioRepositoryImpl
import com.example.receitas.data.receita.repository.ReceitasRepositoryImpl
import com.example.receitas.domain.formulario.repository.FormularioRepository
import com.example.receitas.domain.receita.repository.ReceitasRepository
import com.example.receitas.domain.receita.useCase.buscaReceita.BuscaReceitaPorId
import com.example.receitas.domain.receita.useCase.buscaReceita.BuscaReceitaPorIdUseCase
import com.example.receitas.domain.receita.useCase.buscaReceita.BuscaTodasReceitas
import com.example.receitas.domain.receita.useCase.buscaReceita.BuscaTodasReceitasUseCase
import com.example.receitas.domain.formulario.useCase.BuscaDescricaoPeloId
import com.example.receitas.domain.formulario.useCase.BuscaDescricaoPeloIdUseCase
import com.example.receitas.domain.formulario.useCase.BuscaIdPelaDescricao
import com.example.receitas.domain.formulario.useCase.BuscaIdPelaDescricaoUseCase
import com.example.receitas.domain.formulario.useCase.nivel.BuscaTodosNiveis
import com.example.receitas.domain.formulario.useCase.nivel.BuscaTodosNiveisUseCase
import com.example.receitas.domain.formulario.useCase.tipo.BuscaTodosTipos
import com.example.receitas.domain.formulario.useCase.tipo.BuscaTodosTiposUseCase
import com.example.receitas.domain.receita.useCase.criaReceita.SalvaReceita
import com.example.receitas.domain.receita.useCase.criaReceita.SalvaReceitaUseCase
import com.example.receitas.domain.receita.useCase.deletaReceita.DeletaReceita
import com.example.receitas.domain.receita.useCase.deletaReceita.DeletaReceitaUseCase
import com.example.receitas.domain.receita.useCase.deletaReceita.DeletaTodasReceitas
import com.example.receitas.domain.receita.useCase.deletaReceita.DeletaTodasReceitasUseCase
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
    factory<FormularioRepository> { FormularioRepositoryImpl(get(), get(), get(), get()) }
}