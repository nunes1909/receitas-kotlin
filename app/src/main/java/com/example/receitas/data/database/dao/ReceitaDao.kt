package com.example.receitas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceitaDao {

    @Insert
    suspend fun salva(receita: Receita)

    @Query("SELECT * FROM Receita ORDER BY id DESC")
    fun buscaTodasReceitas(): Flow<List<Receita>?>

}