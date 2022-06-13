package com.example.receitas.domain.useCase.carregaFormulario

import com.example.receitas.data.repository.DataReceitasRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementação do caso de uso que busca todos os Niveis
 */

class BuscaTodosNiveis(
    private val repository: DataReceitasRepository
) : BuscaTodosNiveisUseCase {
    override fun invoke(): Flow<List<String>> {
        return repository.buscaNivelValues()
    }
}