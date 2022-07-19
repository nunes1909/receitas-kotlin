package com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario

import com.example.receitas.domain.model.NivelDomain
import kotlinx.coroutines.flow.Flow

interface BuscaTodosNiveisUseCase {
    operator suspend fun invoke(): Flow<List<NivelDomain>>
}
