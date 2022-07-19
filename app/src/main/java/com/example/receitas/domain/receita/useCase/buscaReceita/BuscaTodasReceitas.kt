package com.example.receitas.domain.receita.useCase.buscaReceita

import com.example.receitas.domain.receita.model.ReceitaDomain
import com.example.receitas.domain.receita.repository.ReceitasRepository
import kotlinx.coroutines.flow.Flow

class BuscaTodasReceitas(
    private val repository: ReceitasRepository
) : BuscaTodasReceitasUseCase {
    override suspend fun invoke(valor: String): Flow<List<ReceitaDomain>> {
        return repository.buscaTodasReceitas(valor)
    }
}