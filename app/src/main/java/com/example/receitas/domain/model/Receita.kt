package com.example.receitas.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Receita(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val titulo: String,
    @ColumnInfo(name = "tipo_id")
    var tipoId: Int,
    @ColumnInfo(name = "nivel_id")
    var nivelId: Int,
    val ingredientes: String? = null,
    val preparo: String? = null,
    val imagem: Bitmap? = null,
    val exibeImagem: Int
) : Parcelable
