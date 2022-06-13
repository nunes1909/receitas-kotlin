package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.domain.model.Receita

interface BuscaReceitaPorIdUseCase {
    suspend operator fun invoke(id: Long): Receita?
}