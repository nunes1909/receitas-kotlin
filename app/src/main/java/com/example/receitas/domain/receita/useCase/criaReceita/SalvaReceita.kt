package com.example.receitas.domain.receita.useCase.criaReceita

import com.example.receitas.domain.receita.model.ReceitaDomain
import com.example.receitas.domain.receita.repository.ReceitasRepository

class SalvaReceita(
    private val repository: ReceitasRepository
) : SalvaReceitaUseCase {
    override suspend fun invoke(receita: ReceitaDomain): Boolean {
        return repository.salvaReceita(receita = receita)
    }
}