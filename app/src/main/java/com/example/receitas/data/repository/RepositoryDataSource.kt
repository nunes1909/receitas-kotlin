package com.example.receitas.data.repository

import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

class RepositoryDataSource(
    private val receitaDao: ReceitaDao
): ReceitasRepository {

    override suspend fun salvaReceita(receita: Receita) {
        receitaDao.salva(receita)
    }

    override fun buscaTodasReceitas(): Flow<List<Receita>?> {
        return receitaDao.buscaTodasReceitas()
    }

}