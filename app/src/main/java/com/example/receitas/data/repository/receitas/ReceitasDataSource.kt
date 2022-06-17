package com.example.receitas.data.repository.receitas

import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

interface ReceitasDataSource {

    // Salva receita
    suspend fun salvaReceita(receita: Receita): Boolean

    // Busca todas receitas
    fun buscaTodasReceitas(valor: String): Flow<List<Receita>?>

    // Busca receita por id
    suspend fun buscaReceitaPorId(id: Long): Receita?

    // Deleta receita
    suspend fun deletaReceita(id: Long): Boolean

    // Deleta Todas Receitas
    suspend fun removeTodasReceitas(): Boolean
}