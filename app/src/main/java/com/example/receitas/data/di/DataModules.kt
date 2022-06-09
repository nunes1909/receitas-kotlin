package com.example.receitas.data.di

import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.data.repository.RepositoryDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = dataModule()

private fun dataModule(): Module{
    return module {
        factory { get<ReceitaDatabase>().receitaDao() }
        factory { get<ReceitaDatabase>().tipoReceitaDao() }
        factory { get<ReceitaDatabase>().nivelReceitaDao() }
        factory { ReceitaDatabase.getInstance(get()) }
        factory { RepositoryDataSource(get()) }
    }
}