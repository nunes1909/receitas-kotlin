package com.example.receitas.domain.usecase.buscaReceita

import com.example.receitas.domain.model.Receita

interface BuscaReceitaPorIdUseCase {
    suspend operator fun invoke(id: Long): Receita
}