package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.domain.model.ReceitaDomain
import kotlinx.coroutines.flow.Flow

interface BuscaTodasReceitasUseCase {
    suspend operator fun invoke(valor: String): Flow<List<ReceitaDomain>>
}