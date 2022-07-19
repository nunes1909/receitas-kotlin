package com.example.receitas.data.di

import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.data.mapper.DataMapper
import com.example.receitas.data.repository.FormularioRepositoryImpl
import org.koin.dsl.module

val dataBaseModules = module {
    factory { get<ReceitaDatabase>().receitaDao() }
    factory { get<ReceitaDatabase>().tipoReceitaDao() }
    factory { get<ReceitaDatabase>().nivelReceitaDao() }
    single { ReceitaDatabase.getInstance(get()) }
}

val repositoryModules = module {
//    factory<ReceitasRepository> { ReceitasRepositoryImpl(get()) }
    factory { DataMapper(get(), get()) }
    factory { FormularioRepositoryImpl(get(), get()) }
}

val dataModules = listOf(
    dataBaseModules,
    repositoryModules
)