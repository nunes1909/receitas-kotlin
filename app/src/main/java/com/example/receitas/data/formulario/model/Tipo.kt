package com.example.receitas.data.formulario.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tipo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val descricao: String
)