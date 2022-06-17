package com.example.receitas.domain.useCase.buscaReceita

import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

interface BuscaTodasReceitasUseCase {
    operator fun invoke(valor: String): Flow<List<Receita>>
}