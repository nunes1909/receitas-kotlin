package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.domain.model.ReceitaDomain
import com.example.receitas.domain.repository.ReceitasRepository
import kotlinx.coroutines.flow.Flow

class BuscaTodasReceitas(
    private val repository: ReceitasRepository
) : BuscaTodasReceitasUseCase {
    override suspend fun invoke(valor: String): Flow<List<ReceitaDomain>> {
        return repository.buscaTodasReceitas(valor)
    }
}