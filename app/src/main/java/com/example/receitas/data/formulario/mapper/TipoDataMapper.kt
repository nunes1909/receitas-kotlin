package com.example.receitas.data.formulario.mapper

import com.example.receitas.data.formulario.model.Tipo
import com.example.receitas.domain.formulario.model.TipoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TipoDataMapper {

    suspend fun paraFlowDomain(flowTipo: Flow<List<Tipo>>) = flow {
        flowTipo.collect { lista ->
            emit(
                lista.map { tipo ->
                    paraDomain(tipo)
                }
            )
        }
    }

    private fun paraDomain(tipo: Tipo) = TipoDomain(
        id = tipo.id,
        descricao = tipo.descricao
    )
}