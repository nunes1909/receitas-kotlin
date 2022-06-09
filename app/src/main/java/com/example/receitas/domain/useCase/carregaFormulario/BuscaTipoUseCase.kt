package com.example.receitas.domain.useCase.carregaFormulario

import com.example.receitas.domain.model.TipoReceita
import kotlinx.coroutines.flow.Flow

interface BuscaTipoUseCase {
    operator fun invoke(): Flow<List<TipoReceita>>
}
