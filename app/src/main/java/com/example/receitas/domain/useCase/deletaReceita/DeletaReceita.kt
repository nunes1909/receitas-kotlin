package com.example.receitas.domain.useCase.deletaReceita

import com.example.receitas.domain.repository.ReceitasRepository

class DeletaReceita(
    private val repository: ReceitasRepository
) : DeletaReceitaUseCase {
    override suspend fun invoke(id: Long): Boolean {
        return repository.deletaReceita(id)
    }
}