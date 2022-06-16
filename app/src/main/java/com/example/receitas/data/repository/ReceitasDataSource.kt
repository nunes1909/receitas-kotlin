package com.example.receitas.data.repository

import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

/**
 * Interface de busca dos dados internamente
 */

interface ReceitasDataSource {

    // Salva receita
    suspend fun salvaReceita(receita: Receita): Boolean

    // Busca todas receitas
    fun buscaTodasReceitas(): Flow<List<Receita>?>

    // Busca receita por id
    suspend fun buscaReceitaPorId(id: Long): Receita?

    // Deleta receita
    suspend fun deletaReceita(id: Long): Boolean

    // Deleta Todas Receitas
    suspend fun removeTodasReceitas(): Boolean

    // Busca value Tipo
    fun buscaTipoValues(): Flow<List<String>>

    // Busca value Nivel
    fun buscaNivelValues(): Flow<List<String>>

    // Busca o Id de tipo e nivel
    suspend fun buscaTipoIdPelaDescricao(descricao: String): Int

    suspend fun buscaNivelIdPelaDescricao(descricao: String): Int

    suspend fun buscaTipoDescricaoPeloId(id: Int): String

    suspend fun buscaNivelDescricaoPeloId(id: Int): String

}