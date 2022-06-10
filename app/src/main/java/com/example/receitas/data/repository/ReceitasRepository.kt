package com.example.receitas.data.repository

import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

interface ReceitasRepository {

    suspend fun salvaReceita(receita: Receita): Boolean

    fun buscaTodasReceitas(): Flow<List<Receita>?>

    fun buscaTipo(): Flow<List<String>>

    fun buscaNivel(): Flow<List<String>>
}