package com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario

import com.example.receitas.domain.repository.FormularioRepository
import kotlinx.coroutines.flow.Flow

class BuscaTodosNiveis(
    private val repository: FormularioRepository
) : BuscaTodosNiveisUseCase {
    override fun invoke(): Flow<List<String>> {
        return repository.buscaNivelValues()
    }
}