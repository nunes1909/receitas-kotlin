package com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario

import kotlinx.coroutines.flow.Flow

interface BuscaTodosNiveisUseCase {
    operator fun invoke(): Flow<List<String>>
}
