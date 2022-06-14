package com.example.receitas.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Receita(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val titulo: String,
    var tipoId: Int,
    var nivelId: Int,
    val ingredientes: String? = null,
    val preparo: String? = null
) : Parcelable
