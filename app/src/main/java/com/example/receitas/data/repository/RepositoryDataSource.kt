package com.example.receitas.data.repository

import com.example.receitas.data.database.dao.NivelReceitaDao
import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.data.database.dao.TipoReceitaDao
import com.example.receitas.domain.model.NivelReceita
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.model.TipoReceita
import kotlinx.coroutines.flow.Flow

class RepositoryDataSource(
    private val receitaDao: ReceitaDao,
    private val tipoDao: TipoReceitaDao,
    private val nivelDao: NivelReceitaDao
): ReceitasRepository {

    override suspend fun salvaReceita(receita: Receita) {
        receitaDao.salva(receita)
    }

    override fun buscaTodasReceitas(): Flow<List<Receita>?> {
        return receitaDao.buscaTodasReceitas()
    }

    override fun buscaTipo(): Flow<List<TipoReceita>> {
        return tipoDao.buscaTipo()
    }

    override fun buscaNivel(): Flow<List<NivelReceita>> {
        return nivelDao.buscaNivel()
    }

}