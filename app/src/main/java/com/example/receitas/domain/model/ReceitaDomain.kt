package com.example.receitas.domain.model

import android.graphics.Bitmap

class ReceitaDomain(
    val id: Long,
    val titulo: String,
    var tipoId: String,
    var nivelId: String,
    val ingredientes: String? = null,
    val preparo: String? = null,
    val imagem: Bitmap? = null,
    val exibeImagem: Int
)



