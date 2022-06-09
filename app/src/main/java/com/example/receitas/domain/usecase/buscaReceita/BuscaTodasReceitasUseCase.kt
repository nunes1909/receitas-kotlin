package com.example.receitas.domain.usecase.buscaReceita

import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

interface BuscaTodasReceitasUseCase {
    suspend operator fun invoke(): Flow<List<Receita>>
}