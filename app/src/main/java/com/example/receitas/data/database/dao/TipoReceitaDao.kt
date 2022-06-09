package com.example.receitas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitas.domain.model.TipoReceita

@Dao
interface TipoReceitaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaTipo(vararg tipoReceita: TipoReceita)

    @Query("SELECT * FROM TipoReceita WHERE id = :id")
    suspend fun buscaTipo(id: Int): TipoReceita
}