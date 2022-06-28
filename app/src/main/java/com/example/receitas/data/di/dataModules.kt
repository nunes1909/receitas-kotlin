package com.example.receitas.data.di

import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.data.repository.FormularioRepositoryImpl
import com.example.receitas.data.repository.ReceitasRepositoryImpl
import com.example.receitas.domain.repository.FormularioRepository
import com.example.receitas.domain.repository.ReceitasRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val dataBaseModules = module {
    factory { get<ReceitaDatabase>().receitaDao() }
    factory { get<ReceitaDatabase>().tipoReceitaDao() }
    factory { get<ReceitaDatabase>().nivelReceitaDao() }
    single { ReceitaDatabase.getInstance(get()) }
}

val repositoryModules = module {
//    factory<ReceitasRepository> { ReceitasRepositoryImpl(get()) }
    factory { FormularioRepositoryImpl(get(), get()) }
}

val dataModules = listOf(
    dataBaseModules,
    repositoryModules
)