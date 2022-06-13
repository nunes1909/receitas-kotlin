package com.example.receitas.domain.useCase.carregaFormulario

import com.example.receitas.data.repository.DataReceitasRepository
import com.example.receitas.data.resource.Resource
import kotlinx.coroutines.flow.Flow

class BuscaTodosTipos(
    private val repository: DataReceitasRepository
): BuscaTodosTiposUseCase {
    override suspend fun invoke(descricao: String): Resource<Flow<List<String>>, Flow<List<Int>>> {
        return repository.buscaTipoDescricao(descricao)
    }
}