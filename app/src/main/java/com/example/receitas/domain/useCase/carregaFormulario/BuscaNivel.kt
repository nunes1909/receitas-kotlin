package com.example.receitas.domain.useCase.carregaFormulario

import com.example.receitas.data.repository.RepositoryDataSource
import com.example.receitas.domain.model.NivelReceita
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BuscaNivel(
    private val repository: RepositoryDataSource
): BuscaNivelUseCase {
    override fun invoke(): Flow<List<NivelReceita>> {
        return repository.buscaNivel()
    }
}