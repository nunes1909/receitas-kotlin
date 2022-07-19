package com.example.receitas.domain.receita.useCase.deletaReceita

import com.example.receitas.domain.receita.repository.ReceitasRepository

class DeletaTodasReceitas(
    private val repository: ReceitasRepository
) : DeletaTodasReceitasUseCase {
    override suspend fun invoke(): Boolean {
        return repository.removeTodasReceitas()
    }
}