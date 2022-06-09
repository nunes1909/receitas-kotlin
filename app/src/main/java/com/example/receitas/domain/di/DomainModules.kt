package com.example.receitas.domain.di

import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitas
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase
import com.example.receitas.domain.useCase.criaReceita.CriaReceita
import com.example.receitas.domain.useCase.criaReceita.CriaReceitaUseCase
import org.koin.core.module.Module
import org.koin.dsl.module


val domainModule = modules()

private fun modules(): Module{
    return module {
        factory<CriaReceitaUseCase> { CriaReceita(get()) }
        factory<BuscaTodasReceitasUseCase> { BuscaTodasReceitas(get()) }
    }
}