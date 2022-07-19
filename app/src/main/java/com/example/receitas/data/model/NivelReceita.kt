package com.example.receitas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NivelReceita(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val descricao: String
)