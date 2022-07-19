package com.example.receitas.presenter.mapper

import com.example.receitas.data.model.Receita
import com.example.receitas.presenter.model.ReceitaPresenter

/**
 * Classe que recebe e devolve as receitas Presenter e Domain para a View
 */
class ResourceReceita(
    private val presenterReceita: ReceitaPresenter,
    private val receita: Receita
) {
    fun pushReceita() = receita
    fun pushPresenterReceita(): ReceitaPresenter = presenterReceita
}