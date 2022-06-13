package com.example.receitas.domain.useCase.carregaFormulario

import com.example.receitas.data.resource.Resource
import kotlinx.coroutines.flow.Flow

interface BuscaTodosNiveisUseCase {
    operator fun invoke(descricao: String): Resource<Flow<List<String>>, Flow<List<Int>>>
}
