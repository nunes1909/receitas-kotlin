package com.example.receitas.domain.receita.model

import android.graphics.Bitmap

class ReceitaDomain(
    val id: Long,
    val titulo: String,
    var tipo: String,
    var nivel: String,
    val ingredientes: String? = null,
    val preparo: String? = null,
    val imagem: Bitmap? = null,
    val exibeImagem: Int
)



