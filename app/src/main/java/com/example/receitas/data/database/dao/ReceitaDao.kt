package com.example.receitas.data.database.dao

import androidx.room.*
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceitaDao {

    @Insert
    suspend fun salva(receita: Receita)

    @Update
    suspend fun edita(receita: Receita)

    @Query("SELECT * FROM Receita ORDER BY id DESC")
    fun buscaTodasReceitas(): Flow<List<Receita>?>

    @Query("SELECT * FROM Receita WHERE id = :id")
    suspend fun buscaReceitaPorId(id: Long): Receita

}