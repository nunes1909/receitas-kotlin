package com.example.receitas.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipoReceita(
    @PrimaryKey(autoGenerate = false)
    val id: Long? = null,
    val descricao: String
)