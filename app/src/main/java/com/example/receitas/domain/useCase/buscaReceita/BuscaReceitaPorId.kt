package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.data.repository.receitas.ReceitasRepository
import com.example.receitas.domain.model.Receita

class BuscaReceitaPorId(
    private val repository: ReceitasRepository
) : BuscaReceitaPorIdUseCase {
    override suspend fun invoke(id: Long): Receita {
        return repository.buscaReceitaPorId(id)
    }
}