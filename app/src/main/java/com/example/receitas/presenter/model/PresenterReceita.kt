package com.example.receitas.presenter.model

import android.graphics.Bitmap

data class PresenterReceita(
    val id: Long,
    val titulo: String,
    var tipoId: String,
    var nivelId: String,
    val ingredientes: String? = null,
    val preparo: String? = null,
    val imagem: Bitmap? = null,
    val exibeImagem: Int
)