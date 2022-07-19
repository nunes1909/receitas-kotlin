package com.example.receitas.domain.useCase.criaReceita

import com.example.receitas.domain.model.ReceitaDomain

interface SalvaReceitaUseCase {
    suspend operator fun invoke(receita: ReceitaDomain): Boolean
}