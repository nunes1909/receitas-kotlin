package com.example.receitas.presenter.model

data class PresenterReceita(
    val id: Long,
    val titulo: String,
    var tipoId: String,
    var nivelId: String,
    val ingredientes: String? = null,
    val preparo: String? = null
)