package com.example.receitas.domain.useCase.carregaFormulario

import com.example.receitas.domain.model.NivelReceita
import kotlinx.coroutines.flow.Flow

interface BuscaNivelUseCase {
    operator fun invoke(): Flow<List<NivelReceita>>
}
