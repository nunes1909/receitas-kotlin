package com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario

import com.example.receitas.data.repository.ReceitasRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementação do caso de uso que busca todos os Niveis
 */

class BuscaTodosNiveis(
    private val repository: ReceitasRepository
) : BuscaTodosNiveisUseCase {
    override fun invoke(): Flow<List<String>> {
        return repository.buscaNivelValues()
    }
}