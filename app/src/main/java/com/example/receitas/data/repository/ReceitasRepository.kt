package com.example.receitas.data.repository

import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

interface ReceitasRepository {

    suspend fun salvaReceita(receita: Receita)

    fun buscaTodasReceitas(): Flow<List<Receita>?>
}