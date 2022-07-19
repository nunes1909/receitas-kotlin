package com.example.receitas.domain.receita.useCase.deletaReceita

interface DeletaTodasReceitasUseCase {
    suspend operator fun invoke(): Boolean
}