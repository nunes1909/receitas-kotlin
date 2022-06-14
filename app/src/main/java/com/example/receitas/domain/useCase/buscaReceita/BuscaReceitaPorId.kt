package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.data.repository.ReceitasRepository
import com.example.receitas.domain.model.Receita

/**
 * Implementação do caso de uso que busca a receita pelo Id
 */

class BuscaReceitaPorId(
    private val repository: ReceitasRepository
) : BuscaReceitaPorIdUseCase {
    override suspend fun invoke(id: Long): Receita {
        return repository.buscaReceitaPorId(id)
    }
}