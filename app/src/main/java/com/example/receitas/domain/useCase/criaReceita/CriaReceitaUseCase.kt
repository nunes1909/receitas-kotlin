package com.example.receitas.domain.useCase.criaReceita

import com.example.receitas.domain.model.Receita

interface CriaReceitaUseCase {
    suspend operator fun invoke(receita: Receita): Boolean
}