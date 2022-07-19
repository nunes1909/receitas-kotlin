package com.example.receitas.data.formulario.mapper

import com.example.receitas.data.formulario.model.Nivel
import com.example.receitas.domain.model.NivelDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NivelDataMapper {

    suspend fun paraFlowDomain(flowNivel: Flow<List<Nivel>>) = flow {
        flowNivel.collect { lista ->
            emit(
                lista.map { nivel ->
                    paraDomain(nivel)
                }
            )
        }
    }

    private fun paraDomain(nivel: Nivel) = NivelDomain(
        id = nivel.id,
        descricao = nivel.descricao
    )
}