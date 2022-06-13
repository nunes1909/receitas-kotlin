package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.data.repository.DataReceitasRepository
import com.example.receitas.domain.model.Receita

/**
 * Implementação do caso de uso que busca a receita pelo Id
 */

class BuscaReceitaPorId(
    private val repository: DataReceitasRepository
) : BuscaReceitaPorIdUseCase {
    override suspend fun invoke(id: Long): Receita {
        return repository.buscaReceitaPorId(id)
    }
}