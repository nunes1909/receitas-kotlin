package com.example.receitas.domain.formulario.useCase.tipo

import com.example.receitas.domain.formulario.model.TipoDomain
import com.example.receitas.domain.formulario.repository.FormularioRepository
import kotlinx.coroutines.flow.Flow

class BuscaTodosTipos(
    private val repository: FormularioRepository
) : BuscaTodosTiposUseCase {
    override suspend fun invoke(): Flow<List<TipoDomain>> {
        return repository.buscaTipoValues()
    }
}