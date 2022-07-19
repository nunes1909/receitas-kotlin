package com.example.receitas.domain.receita.useCase.deletaReceita

interface DeletaReceitaUseCase {
    suspend operator fun invoke(id: Long): Boolean
}