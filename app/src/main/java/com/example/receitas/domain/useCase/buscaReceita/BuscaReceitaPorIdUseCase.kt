package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.domain.model.Receita

/**
 * Caso de uso que busca uma receita pelo Id
 */

interface BuscaReceitaPorIdUseCase {
    suspend operator fun invoke(id: Long): Receita
}