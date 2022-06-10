package com.example.receitas.data.repository

import com.example.receitas.data.database.dao.NivelReceitaDao
import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.data.database.dao.TipoReceitaDao
import com.example.receitas.domain.model.NivelReceita
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.model.TipoReceita
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RepositoryDataSource(
    private val receitaDao: ReceitaDao,
    private val tipoDao: TipoReceitaDao,
    private val nivelDao: NivelReceitaDao
) : ReceitasRepository {

    override suspend fun salvaReceita(receita: Receita): Boolean {
        return try {
            receitaDao.salva(receita)
            true
        } catch (e: Exception) {
            false
        }
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