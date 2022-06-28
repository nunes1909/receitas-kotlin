package com.example.receitas.domain.repository

import kotlinx.coroutines.flow.Flow

interface FormularioRepository {

    // Busca value Tipo
    fun buscaTipoValues(): Flow<List<String>>

    // Busca value Nivel
    fun buscaNivelValues(): Flow<List<String>>

    // Busca o id do tipo pela descrição
    suspend fun buscaTipoIdPelaDescricao(descricao: String): Int

    // Busca descrição do tipo pelo id
    suspend fun buscaTipoDescricaoPeloId(id: Int): String

    // Busca o id do nivel pela descrição
    suspend fun buscaNivelIdPelaDescricao(descricao: String): Int

    // Busca descrição do nivel pelo id
    suspend fun buscaNivelDescricaoPeloId(id: Int): String
}