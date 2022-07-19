package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.domain.model.ReceitaDomain

interface BuscaReceitaPorIdUseCase {
    suspend operator fun invoke(id: Long): ReceitaDomain
}