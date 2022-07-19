package com.example.receitas.presenter.model

import android.graphics.Bitmap

data class ReceitaPresenter(
    val id: Long,
    val titulo: String,
    var tipo: String,
    var nivel: String,
    val ingredientes: String? = null,
    val preparo: String? = null,
    val imagem: Bitmap? = null,
    val exibeImagem: Int
) : TipoNivelPresenter()

open class TipoNivelPresenter(
    val tipoId: Int? = null,
    val nivelId: Int? = null,
)