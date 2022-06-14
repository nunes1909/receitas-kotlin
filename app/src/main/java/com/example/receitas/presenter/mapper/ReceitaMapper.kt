package com.example.receitas.presenter.mapper

import com.example.receitas.domain.model.Receita
import com.example.receitas.presenter.model.PresenterReceita

/**
 * Classe responsável por transformar a Model da Domain (principal)
 * na model da Presenter
 */
class ReceitaMapper {

    // Transforma a Presenter em Domain
    fun dePresenterParaDomain(receita: PresenterReceita): Receita {

        val tipoId: Int = when (receita.tipoId) {
            "Refeição" -> 1
            "Lanche" -> 2
            else -> 3
        }
        val nivelId: Int = when (receita.nivelId) {
            "Fácil" -> 1
            "Médio" -> 2
            else -> 3
        }

        return Receita(
            id = receita.id,
            titulo = receita.titulo,
            tipoId = tipoId,
            nivelId = nivelId,
            ingredientes = receita.ingredientes,
            preparo = receita.preparo
        )
    }

    // Transforma a Domain em Presenter
    fun deDomainParaPresenter(receita: Receita): PresenterReceita {

        val tipo: String = when (receita.tipoId) {
            1 -> "Refeição"
            2 -> "Lanche"
            else -> "Drink"
        }
        val nivel: String = when (receita.nivelId) {
            1 -> "Fácil"
            2 -> "Médio"
            else -> "Difícil"
        }

        return PresenterReceita(
            id = receita.id,
            titulo = receita.titulo,
            tipoId = tipo,
            nivelId = nivel,
            ingredientes = receita.ingredientes,
            preparo = receita.preparo
        )
    }
}