package com.example.receitas.domain.formulario.repository

import com.example.receitas.domain.formulario.model.NivelDomain
import com.example.receitas.domain.formulario.model.TipoDomain
import kotlinx.coroutines.flow.Flow

interface FormularioRepository {

    // Busca value Tipo
    suspend fun buscaTipoValues(): Flow<List<TipoDomain>>

    // Busca value Nivel
    suspend fun buscaNivelValues(): Flow<List<NivelDomain>>

    // Busca o id do tipo pela descrição
    suspend fun buscaTipoIdPelaDescricao(descricao: String): Int

    // Busca descrição do tipo pelo id
    suspend fun buscaTipoDescricaoPeloId(id: Int): String

    // Busca o id do nivel pela descrição
    suspend fun buscaNivelIdPelaDescricao(descricao: String): Int

    // Busca descrição do nivel pelo id
    suspend fun buscaNivelDescricaoPeloId(id: Int): String
}