package com.example.receitas.presenter.mapper

import com.example.receitas.domain.model.Receita
import com.example.receitas.presenter.model.PresenterReceita

class ReceitaMapper {
    fun convertReceita(receita: PresenterReceita): Receita{
        var tipoId: Int? = null
        var nivelId: Int? = null

        when(receita.tipoId){
            "Refeição" -> tipoId = 1
            "Lanche" -> tipoId = 2
            "Drink" -> tipoId = 3
            else -> null
        }
        when(receita.nivelId){
            "Fácil" -> nivelId = 1
            "Médio" -> nivelId = 2
            "Difícil" -> nivelId = 3
            else -> null
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
}