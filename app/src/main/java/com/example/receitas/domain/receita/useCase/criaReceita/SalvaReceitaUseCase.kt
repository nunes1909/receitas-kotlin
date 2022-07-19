package com.example.receitas.domain.receita.useCase.criaReceita

import com.example.receitas.domain.receita.model.ReceitaDomain

interface SalvaReceitaUseCase {
    suspend operator fun invoke(receita: ReceitaDomain): Boolean
}