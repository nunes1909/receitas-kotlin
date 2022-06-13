package com.example.receitas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitas.domain.model.TipoReceita
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoReceitaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaTipo(vararg tipoReceita: TipoReceita)

    @Query("SELECT descricao FROM TipoReceita")
    fun buscaTipos(): Flow<List<String>>
}