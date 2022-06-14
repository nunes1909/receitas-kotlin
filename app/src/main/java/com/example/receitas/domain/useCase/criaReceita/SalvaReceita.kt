package com.example.receitas.domain.useCase.criaReceita

import com.example.receitas.data.repository.ReceitasRepository
import com.example.receitas.domain.model.Receita

/**
 * Implementação do caso de uso de salvar a receita
 */

class SalvaReceita(
    private val repository: ReceitasRepository
) : SalvaReceitaUseCase {
    override suspend fun invoke(receita: Receita): Boolean {
        return repository.salvaReceita(receita = receita)
    }
}