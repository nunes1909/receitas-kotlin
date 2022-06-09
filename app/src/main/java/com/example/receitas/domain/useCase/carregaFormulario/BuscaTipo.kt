package com.example.receitas.domain.useCase.carregaFormulario

import com.example.receitas.data.repository.RepositoryDataSource
import com.example.receitas.domain.model.TipoReceita
import kotlinx.coroutines.flow.Flow

class BuscaTipo(
    private val repository: RepositoryDataSource
): BuscaTipoUseCase {
    override fun invoke(): Flow<List<TipoReceita>> {
        return repository.buscaTipo()
    }
}