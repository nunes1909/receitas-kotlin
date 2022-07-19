package com.example.receitas.domain.useCase.criaReceita

import com.example.receitas.data.model.Receita
import com.example.receitas.domain.repository.ReceitasRepository

class SalvaReceita(
    private val repository: ReceitasRepository
) : SalvaReceitaUseCase {
    override suspend fun invoke(receita: Receita): Boolean {
        return repository.salvaReceita(receita = receita)
    }
}