package com.example.receitas.presenter.mapper

import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.useCase.buscaTipoNivel.BuscaDescricaoPeloIdUseCase
import com.example.receitas.domain.useCase.buscaTipoNivel.BuscaIdPelaDescricaoUseCase
import com.example.receitas.presenter.model.PresenterReceita

/**
 * Classe responsável por transformar a Model da Domain (principal)
 * na model da Presenter
 */
class ReceitaMapper(
    private val buscaIdPelaDescricaoUseCase: BuscaIdPelaDescricaoUseCase,
    private val buscaDescricaoPeloIdUseCase: BuscaDescricaoPeloIdUseCase
) {
    // Transforma a Presenter em Domain
    suspend fun dePresenterParaDomain(presenterReceita: PresenterReceita): Receita {

        val tipoId = buscaIdPelaDescricaoUseCase.buscaTipoId(presenterReceita.tipoId)
        val nivelId = buscaIdPelaDescricaoUseCase.buscaNivelId(presenterReceita.nivelId)

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
    suspend fun deDomainParaPresenter(receita: Receita): PresenterReceita {

        val tipo = buscaDescricaoPeloIdUseCase.buscaTipoDescricao(receita.tipoId)
        val nivel = buscaDescricaoPeloIdUseCase.buscaNivelDescricao(receita.nivelId)

        return PresenterReceita(
            id = receita.id,
            titulo = receita.titulo,
            tipoId = tipo,
            nivelId = nivel,
            ingredientes = receita.ingredientes,
            preparo = receita.preparo,
            imagem = receita.imagem,
            exibeImagem = receita.exibeImagem
        )
    }
}