package com.example.receitas.domain.usecase.criaReceita

import com.example.receitas.domain.model.Receita

interface CriaReceitaUseCase {
    suspend operator fun invoke(receita: Receita)
}