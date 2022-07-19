package com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario

import com.example.receitas.domain.model.NivelDomain
import com.example.receitas.domain.repository.FormularioRepository
import kotlinx.coroutines.flow.Flow

class BuscaTodosNiveis(
    private val repository: FormularioRepository
) : BuscaTodosNiveisUseCase {
    override suspend fun invoke(): Flow<List<NivelDomain>> {
        return repository.buscaNivelValues()
    }
}