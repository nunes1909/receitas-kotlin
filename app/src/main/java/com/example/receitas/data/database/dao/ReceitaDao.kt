package com.example.receitas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceitaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(dataReceita: Receita)

    @Query("SELECT * FROM Receita ORDER BY id DESC")
    fun buscaTodasReceitas(): Flow<List<Receita>?>

    @Query("SELECT * FROM Receita WHERE id = :id")
    suspend fun buscaReceitaPorId(id: Long): Receita

}