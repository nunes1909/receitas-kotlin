package com.example.receitas.domain.formulario.useCase.tipo

import com.example.receitas.domain.formulario.model.TipoDomain
import kotlinx.coroutines.flow.Flow

interface BuscaTodosTiposUseCase {
    suspend operator fun invoke(): Flow<List<TipoDomain>>
}
