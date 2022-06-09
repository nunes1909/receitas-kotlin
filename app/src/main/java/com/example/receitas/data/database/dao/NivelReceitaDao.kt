package com.example.receitas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitas.domain.model.NivelReceita

@Dao
interface NivelReceitaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaNivel(vararg nivel: NivelReceita)

    @Query("SELECT * FROM NivelReceita WHERE id = :id")
    suspend fun buscaNivel(id: Int): NivelReceita
}