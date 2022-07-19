package com.example.receitas.data.formulario.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitas.data.formulario.model.Tipo
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaTipo(vararg tipoReceita: Tipo)

    @Query("SELECT * FROM Tipo")
    fun buscaTipos(): Flow<List<Tipo>>

    @Query("SELECT id FROM Tipo WHERE descricao = :descricao")
    suspend fun buscaId(descricao: String): Int

    @Query("SELECT descricao FROM Tipo WHERE id = :id")
    suspend fun buscaDescricao(id: Int): String
}