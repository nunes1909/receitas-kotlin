package com.example.receitas.domain.useCase.carregaFormulario

import com.example.receitas.data.repository.RepositoryDataSource
import com.example.receitas.domain.model.NivelReceita
import kotlinx.coroutines.flow.Flow

class BuscaNivel(
    private val repository: RepositoryDataSource
): BuscaNivelUseCase {
    override fun invoke(): Flow<List<NivelReceita>> {
        return repository.buscaNivel()
    }
}