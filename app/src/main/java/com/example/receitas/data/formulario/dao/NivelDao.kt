package com.example.receitas.data.formulario.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitas.data.formulario.model.Nivel
import kotlinx.coroutines.flow.Flow

@Dao
interface NivelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaNivel(vararg nivel: Nivel)

    @Query("SELECT * FROM Nivel")
    fun buscaNiveis(): Flow<List<Nivel>>

    @Query("SELECT id FROM Nivel WHERE descricao = :descricao")
    suspend fun buscaId(descricao: String): Int

    @Query("SELECT descricao FROM Nivel WHERE id = :id")
    suspend fun buscaDescricao(id: Int): String
}