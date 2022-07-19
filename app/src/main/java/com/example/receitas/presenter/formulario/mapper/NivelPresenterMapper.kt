package com.example.receitas.presenter.formulario.mapper

import com.example.receitas.domain.formulario.model.NivelDomain
import com.example.receitas.presenter.formulario.model.NivelPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NivelPresenterMapper {

    suspend fun paraFlowPresenter(flowDomain: Flow<List<NivelDomain>>) = flow {
        flowDomain.collect{ lista ->
            emit(
                lista.map { nivelDomain ->
                    paraPresenter(nivelDomain)
                }
            )
        }
    }

    private fun paraPresenter(nivelDomain: NivelDomain) = NivelPresenter(
        descricao = nivelDomain.descricao
    )
}