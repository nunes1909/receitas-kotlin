package com.example.receitas.domain.useCase.criaReceita

import com.example.receitas.data.model.Receita

interface SalvaReceitaUseCase {
    suspend operator fun invoke(receita: Receita): Boolean
}