package com.example.receitas.presenter.receita.mapper

import com.example.receitas.domain.receita.model.ReceitaDomain
import com.example.receitas.domain.formulario.useCase.BuscaIdPelaDescricaoUseCase
import com.example.receitas.presenter.model.ReceitaPresenter
import com.example.receitas.presenter.model.TipoNivelPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReceitaPresenterMapper(
    private val buscaIdPelaDescricaoUseCase: BuscaIdPelaDescricaoUseCase
) {
    suspend fun paraFlowPresenter(flowDomain: Flow<List<ReceitaDomain>>) = flow {
        flowDomain.collect { lista ->
            emit(
                lista.map { receitaDomain ->
                    paraPresenter(receitaDomain)
                }
            )
        }
    }

    fun paraPresenter(receitaDomain: ReceitaDomain): ReceitaPresenter {
        return ReceitaPresenter(
            id = receitaDomain.id,
            titulo = receitaDomain.titulo,
            tipo = receitaDomain.tipo,
            nivel = receitaDomain.nivel,
            ingredientes = receitaDomain.ingredientes,
            preparo = receitaDomain.preparo,
            imagem = receitaDomain.imagem,
            exibeImagem = receitaDomain.exibeImagem
        )
    }

    fun paraDomain(receitaPresenter: ReceitaPresenter) = ReceitaDomain(
        id = receitaPresenter.id,
        titulo = receitaPresenter.titulo,
        tipo = receitaPresenter.tipo,
        nivel = receitaPresenter.nivel,
        ingredientes = receitaPresenter.ingredientes,
        preparo = receitaPresenter.preparo,
        imagem = receitaPresenter.imagem,
        exibeImagem = receitaPresenter.exibeImagem
    )

    suspend fun buscaIdTipoNivel(nivel: String, tipo: String): TipoNivelPresenter {
        val nivelId = buscaIdPelaDescricaoUseCase.buscaNivelId(nivel)
        val tipoId = buscaIdPelaDescricaoUseCase.buscaTipoId(tipo)

        return TipoNivelPresenter(
            tipoId = tipoId,
            nivelId = nivelId
        )
    }
}