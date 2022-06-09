package com.example.receitas.domain.usecase.deletaReceita

interface DeletaReceitaUseCase {
    suspend operator fun invoke(id: Long)
}