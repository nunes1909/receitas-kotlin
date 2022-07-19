package com.example.receitas.presenter.mapper

import com.example.receitas.domain.model.ReceitaDomain
import com.example.receitas.presenter.model.ReceitaPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PresenterMapper {
    suspend fun paraFlowPresenter(flowDomain: Flow<List<ReceitaDomain>>) = flow {
        flowDomain.collect { lista ->
            emit(
                lista.map { receitaDomain ->
                    paraPresenter(receitaDomain)
                }
            )
        }
    }

    private fun paraPresenter(receitaDomain: ReceitaDomain) = ReceitaPresenter(
        id = receitaDomain.id,
        titulo = receitaDomain.titulo,
        tipoId = receitaDomain.tipoId,
        nivelId = receitaDomain.nivelId,
        ingredientes = receitaDomain.ingredientes,
        preparo = receitaDomain.preparo,
        imagem = receitaDomain.imagem,
        exibeImagem = receitaDomain.exibeImagem
    )

    suspend fun paraDomain(receitaPresenter: ReceitaPresenter) = ReceitaDomain(
        id = receitaPresenter.id,
        titulo = receitaPresenter.titulo,
        tipoId = receitaPresenter.tipoId,
        nivelId = receitaPresenter.nivelId,
        ingredientes = receitaPresenter.ingredientes,
        preparo = receitaPresenter.preparo,
        imagem = receitaPresenter.imagem,
        exibeImagem = receitaPresenter.exibeImagem
    )
}