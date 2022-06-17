package com.example.receitas.domain.useCase.deletaReceita

import com.example.receitas.data.repository.receitas.ReceitasRepository

class DeletaTodasReceitas(
    private val repository: ReceitasRepository
) : DeletaTodasReceitasUseCase {
    override suspend fun invoke(): Boolean {
        return repository.removeTodasReceitas()
    }
}