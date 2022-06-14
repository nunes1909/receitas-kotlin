package com.example.receitas.domain.useCase.deletaReceita

import com.example.receitas.data.repository.ReceitasRepository

/**
 * AQUI DEVE SER FEITO O DELEGATE DE DELETAR UMA RECEITA PARA O REPOSITORY
 */

class DeletaReceita(
    private val repository: ReceitasRepository
): DeletaReceitaUseCase{
    override suspend fun invoke(id: Long): Boolean {
        return repository.deletaReceita(id)
    }
}