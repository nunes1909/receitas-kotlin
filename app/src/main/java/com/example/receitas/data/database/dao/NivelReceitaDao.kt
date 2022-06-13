package com.example.receitas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitas.domain.model.NivelReceita
import kotlinx.coroutines.flow.Flow

@Dao
interface NivelReceitaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaNivel(vararg nivel: NivelReceita)

    @Query("SELECT descricao FROM NivelReceita")
    fun buscaNivelDesc(): Flow<List<String>>

    @Query("SELECT id FROM NivelReceita WHERE descricao = :descricao")
    fun buscaNivelId(descricao: String): Flow<List<Int>>
}