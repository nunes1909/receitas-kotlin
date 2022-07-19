package com.example.receitas.domain.receita.useCase.buscaReceita

import com.example.receitas.domain.receita.model.ReceitaDomain
import kotlinx.coroutines.flow.Flow

interface BuscaTodasReceitasUseCase {
    suspend operator fun invoke(valor: String): Flow<List<ReceitaDomain>>
}