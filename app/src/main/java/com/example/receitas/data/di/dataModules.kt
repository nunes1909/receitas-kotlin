package com.example.receitas.data.di

import com.example.receitas.data.database.ReceitaDatabase
import com.example.receitas.data.formulario.mapper.NivelDataMapper
import com.example.receitas.data.formulario.mapper.TipoDataMapper
import com.example.receitas.data.receita.mapper.ReceitaDataMapper
import com.example.receitas.data.formulario.repository.FormularioRepositoryImpl
import org.koin.dsl.module

val dataBaseModules = module {
    factory { get<ReceitaDatabase>().receitaDao() }
    factory { get<ReceitaDatabase>().tipoReceitaDao() }
    factory { get<ReceitaDatabase>().nivelReceitaDao() }
    single { ReceitaDatabase.getInstance(get()) }
}

val repositoryModules = module {
//    factory<ReceitasRepository> { ReceitasRepositoryImpl(get()) }
    factory { ReceitaDataMapper(get(), get()) }
    factory { NivelDataMapper() }
    factory { TipoDataMapper() }
    factory { FormularioRepositoryImpl(get(), get(), get(), get()) }
}

val dataModules = listOf(
    dataBaseModules,
    repositoryModules
)