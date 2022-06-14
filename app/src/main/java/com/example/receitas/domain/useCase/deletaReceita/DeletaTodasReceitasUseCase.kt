package com.example.receitas.domain.useCase.deletaReceita

interface DeletaTodasReceitasUseCase {
    suspend operator fun invoke(): Boolean
}