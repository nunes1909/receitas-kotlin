package com.example.receitas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceitaDao {

    @Insert
    suspend fun salva(receita: Receita)

    @Update
    suspend fun edita(receita: Receita)

    @Query("DELETE FROM Receita WHERE id = :id")
    suspend fun deleta(id: Long)

    @Query("DELETE FROM Receita")
    suspend fun deletaTodas()

    @Query("SELECT * FROM Receita ORDER BY id ASC")
    fun reorderIdCrescente(): Flow<List<Receita>>

    @Query("SELECT * FROM Receita ORDER BY id DESC")
    fun reorderIdDecrescente(): Flow<List<Receita>>

    @Query("SELECT * FROM Receita WHERE id = :id")
    suspend fun buscaReceitaPorId(id: Long): Receita

    @Query("SELECT * FROM Receita ORDER BY tipoId ASC")
    fun reorderTipo(): Flow<List<Receita>>

    @Query("SELECT * FROM Receita ORDER BY nivelId ASC")
    fun reorderNivel(): Flow<List<Receita>>
}