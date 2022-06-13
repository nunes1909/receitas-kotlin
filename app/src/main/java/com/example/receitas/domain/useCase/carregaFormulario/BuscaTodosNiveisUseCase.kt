package com.example.receitas.domain.useCase.carregaFormulario

import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso de buscar todos os niveis de receita
 */

interface BuscaTodosNiveisUseCase {
    operator fun invoke(): Flow<List<String>>
}
