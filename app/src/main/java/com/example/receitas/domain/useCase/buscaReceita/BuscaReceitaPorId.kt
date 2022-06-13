package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.data.repository.DataReceitasRepository
import com.example.receitas.domain.model.Receita

/**
 * AQUI DEVE SER FEITO O DELEGATE DE BUSCAR UMA RECEITA ESPECIFICA PARA O REPOSITORY
 */

class BuscaReceitaPorId(
    private val repository: DataReceitasRepository
): BuscaReceitaPorIdUseCase {
    override suspend fun invoke(id: Long): Receita {
        return repository.buscaReceitaPorId(id)
    }
}