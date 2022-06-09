package com.example.receitas.data.repository

import com.example.receitas.domain.model.NivelReceita
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.model.TipoReceita
import kotlinx.coroutines.flow.Flow

interface ReceitasRepository {

    suspend fun salvaReceita(receita: Receita)

    fun buscaTodasReceitas(): Flow<List<Receita>?>

    fun buscaTipo(): Flow<List<TipoReceita>>

    fun buscaNivel(): Flow<List<NivelReceita>>
}