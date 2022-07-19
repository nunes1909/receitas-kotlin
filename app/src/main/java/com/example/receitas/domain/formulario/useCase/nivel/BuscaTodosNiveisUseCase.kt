package com.example.receitas.domain.formulario.useCase.nivel

import com.example.receitas.domain.formulario.model.NivelDomain
import kotlinx.coroutines.flow.Flow

interface BuscaTodosNiveisUseCase {
    operator suspend fun invoke(): Flow<List<NivelDomain>>
}
