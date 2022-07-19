package com.example.receitas.domain.repository

import com.example.receitas.data.model.Receita
import com.example.receitas.domain.model.ReceitaDomain
import kotlinx.coroutines.flow.Flow

interface ReceitasRepository {

    // Salva receita
    suspend fun salvaReceita(receita: ReceitaDomain): Boolean

    // Busca todas receitas
    suspend fun buscaTodasReceitas(valor: String): Flow<List<ReceitaDomain>>

    // Busca receita por id
    suspend fun buscaReceitaPorId(id: Long): Receita

    // Deleta receita
    suspend fun deletaReceita(id: Long): Boolean

    // Deleta Todas Receitas
    suspend fun removeTodasReceitas(): Boolean
}