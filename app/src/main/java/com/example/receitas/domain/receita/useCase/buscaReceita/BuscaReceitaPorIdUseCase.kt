package com.example.receitas.domain.receita.useCase.buscaReceita

import com.example.receitas.domain.receita.model.ReceitaDomain

interface BuscaReceitaPorIdUseCase {
    suspend operator fun invoke(id: Long): ReceitaDomain
}