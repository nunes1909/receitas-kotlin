package com.example.receitas.domain.receita.useCase.buscaReceita

import com.example.receitas.domain.receita.model.ReceitaDomain
import com.example.receitas.domain.receita.repository.ReceitasRepository

class BuscaReceitaPorId(
    private val repository: ReceitasRepository
) : BuscaReceitaPorIdUseCase {
    override suspend fun invoke(id: Long): ReceitaDomain {
        return repository.buscaReceitaPorId(id)
    }
}