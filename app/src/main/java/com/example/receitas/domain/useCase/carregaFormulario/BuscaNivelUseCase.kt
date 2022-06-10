package com.example.receitas.domain.useCase.carregaFormulario

import kotlinx.coroutines.flow.Flow

interface BuscaNivelUseCase {
    operator fun invoke(): Flow<List<String>>
}
