package com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario

import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso de buscar todos os Tipos de receita
 */

interface BuscaTodosTiposUseCase {
    operator fun invoke(): Flow<List<String>>
}
