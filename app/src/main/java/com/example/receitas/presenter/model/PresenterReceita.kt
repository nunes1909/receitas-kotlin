package com.example.receitas.presenter.model

data class PresenterReceita(
    val id: Long,
    val titulo: String,
    val ingredientes: String,
    val preparo: String? = null,
    var tipoId: String? = null,
    var nivelId: String? = null
//    val uuid: String? = null,
)