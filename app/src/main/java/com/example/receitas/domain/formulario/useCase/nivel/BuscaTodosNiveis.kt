package com.example.receitas.domain.formulario.useCase.nivel

import com.example.receitas.domain.formulario.model.NivelDomain
import com.example.receitas.domain.formulario.repository.FormularioRepository
import kotlinx.coroutines.flow.Flow

class BuscaTodosNiveis(
    private val repository: FormularioRepository
) : BuscaTodosNiveisUseCase {
    override suspend fun invoke(): Flow<List<NivelDomain>> {
        return repository.buscaNivelValues()
    }
}