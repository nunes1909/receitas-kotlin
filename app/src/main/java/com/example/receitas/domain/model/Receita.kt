package com.example.receitas.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Receita(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val titulo: String,
    val descricao: String,
    val tipo: Int? = null,
    val dificuldade: String? = null
//    val uuid: String? = null,
) : Parcelable
