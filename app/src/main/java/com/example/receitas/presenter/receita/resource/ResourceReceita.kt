package com.example.receitas.presenter.receita.resource

import com.example.receitas.presenter.model.ReceitaPresenter
import com.example.receitas.presenter.model.TipoNivelPresenter

/**
 * Classe que recebe e devolve as receitas Presenter e Domain para a View
 */
class ResourceReceita(
    private val presenterReceita: ReceitaPresenter,
    private val tipoNivelPresenter: TipoNivelPresenter
) {
    fun buscaTipoNivel(): TipoNivelPresenter = tipoNivelPresenter
    fun buscaPresenterReceita(): ReceitaPresenter = presenterReceita
}