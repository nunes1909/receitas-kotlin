package com.example.receitas.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NivelReceita(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val descricao: String
)