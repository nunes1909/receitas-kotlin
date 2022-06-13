package com.example.receitas.presenter.model

data class PresenterReceita(
    val id: Long?,
    val titulo: String,
    val ingredientes: String,
    val preparo: String? = null,
    var tipoId: String,
    var nivelId: String
//    val uuid: String? = null,
)