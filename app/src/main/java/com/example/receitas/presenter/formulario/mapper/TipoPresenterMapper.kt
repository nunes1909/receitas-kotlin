package com.example.receitas.presenter.formulario.mapper

import com.example.receitas.domain.formulario.model.TipoDomain
import com.example.receitas.presenter.formulario.model.TipoPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TipoPresenterMapper {

    suspend fun paraFlowPresenter(flowDomain: Flow<List<TipoDomain>>) = flow {
        flowDomain.collect{ lista ->
            emit(
                lista.map { tipoDomain ->
                    paraPresenter(tipoDomain)
                }
            )
        }
    }

    private fun paraPresenter(tipoDomain: TipoDomain) = TipoPresenter(
        descricao = tipoDomain.descricao
    )
}