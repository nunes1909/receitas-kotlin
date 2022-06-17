package com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario

import kotlinx.coroutines.flow.Flow

interface BuscaTodosTiposUseCase {
    operator fun invoke(): Flow<List<String>>
}
