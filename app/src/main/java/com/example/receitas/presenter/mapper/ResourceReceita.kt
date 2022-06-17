package com.example.receitas.presenter.mapper

import com.example.receitas.domain.model.Receita
import com.example.receitas.presenter.model.PresenterReceita

/**
 * Classe que recebe e devolve as receitas Presenter e Domain para a View
 */
class ResourceReceita(
    private val presenterReceita: PresenterReceita,
    private val receita: Receita
) {
    fun pushReceita() = receita
    fun pushPresenterReceita(): PresenterReceita = presenterReceita
}