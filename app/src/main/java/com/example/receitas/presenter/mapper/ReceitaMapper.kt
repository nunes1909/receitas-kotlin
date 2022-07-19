package com.example.receitas.presenter.mapper

import com.example.receitas.data.model.Receita
import com.example.receitas.domain.model.ReceitaDomain
import com.example.receitas.domain.useCase.buscaTipoNivel.BuscaDescricaoPeloIdUseCase
import com.example.receitas.domain.useCase.buscaTipoNivel.BuscaIdPelaDescricaoUseCase
import com.example.receitas.presenter.model.ReceitaPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Classe responsável por transformar a Model da Domain (principal)
 * na model da Presenter
 */
class ReceitaMapper(
    private val buscaIdPelaDescricaoUseCase: BuscaIdPelaDescricaoUseCase,
    private val buscaDescricaoPeloIdUseCase: BuscaDescricaoPeloIdUseCase
) {
    // Transforma a Presenter em Domain
    suspend fun dePresenterParaDomain(presenterReceita: ReceitaPresenter): Receita {

        val tipoId = buscaIdPelaDescricaoUseCase.buscaTipoId(presenterReceita.tipo)
        val nivelId = buscaIdPelaDescricaoUseCase.buscaNivelId(presenterReceita.nivel)

        return Receita(
            id = presenterReceita.id,
            titulo = presenterReceita.titulo,
            tipoId = tipoId,
            nivelId = nivelId,
            ingredientes = presenterReceita.ingredientes,
            preparo = presenterReceita.preparo,
            imagem = presenterReceita.imagem,
            exibeImagem = presenterReceita.exibeImagem
        )
    }

    // Transforma a Domain em Presenter
    suspend fun deDomainParaPresenter(receita: Receita): ReceitaPresenter {

        val tipo = buscaDescricaoPeloIdUseCase.buscaTipoDescricao(receita.tipoId)
        val nivel = buscaDescricaoPeloIdUseCase.buscaNivelDescricao(receita.nivelId)

        return ReceitaPresenter(
            id = receita.id,
            titulo = receita.titulo,
            tipo = tipo,
            nivel = nivel,
            ingredientes = receita.ingredientes,
            preparo = receita.preparo,
            imagem = receita.imagem,
            exibeImagem = receita.exibeImagem
        )
    }
}