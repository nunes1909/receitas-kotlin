package com.example.receitas.domain.useCase.deletaReceita

interface DeletaReceitaUseCase {
    suspend operator fun invoke(id: Long): Boolean
}