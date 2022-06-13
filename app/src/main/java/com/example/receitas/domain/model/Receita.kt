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
    val ingredientes: String,
    val preparo: String? = null,
    var tipoId: Int? = null,
    var nivelId: Int? = null
//    val uuid: String? = null,
) : Parcelable
