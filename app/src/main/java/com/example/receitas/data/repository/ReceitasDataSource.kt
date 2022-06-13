package com.example.receitas.data.repository

import com.example.receitas.data.resource.Resource
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

interface ReceitasDataSource {

    // Salva receita
    suspend fun salvaReceita(receita: Receita): Boolean

    // Busca todas receitas
    fun buscaTodasReceitas(): Flow<List<Receita>?>

    // Busca receita por id
    suspend fun buscaReceitaPorId(id: Long): Receita?

    // Busca value Tipo
    suspend fun buscaTipoDescricao(descricao: String): Resource<Flow<List<String>>, Flow<List<Int>>>

    // Busca value Nivel
    fun buscaNivel(descricao: String): Resource<Flow<List<String>>, Flow<List<Int>>>

}