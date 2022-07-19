package com.example.receitas.data.formulario.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitas.data.formulario.model.TipoReceita
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoReceitaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaTipo(vararg tipoReceita: TipoReceita)

    @Query("SELECT descricao FROM TipoReceita")
    fun buscaTipos(): Flow<List<String>>

    @Query("SELECT id FROM TipoReceita WHERE descricao = :descricao")
    suspend fun buscaId(descricao: String): Int

    @Query("SELECT descricao FROM TipoReceita WHERE id = :id")
    suspend fun buscaDescricao(id: Int): String
}