package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso que busca todas as receitas
 */

interface BuscaTodasReceitasUseCase {
    operator fun invoke(): Flow<List<Receita>>
}