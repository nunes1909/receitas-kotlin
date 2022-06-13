package com.example.receitas.data.repository

import com.example.receitas.data.database.dao.NivelReceitaDao
import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.data.database.dao.TipoReceitaDao
import com.example.receitas.data.resource.Resource
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DataReceitasRepository(
    private val receitaDao: ReceitaDao,
    private val tipoDao: TipoReceitaDao,
    private val nivelDao: NivelReceitaDao
) : ReceitasDataSource {

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

    override suspend fun buscaReceitaPorId(id: Long): Receita {
        return try {
            receitaDao.buscaReceitaPorId(id)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun buscaTipoDescricao(descricao: String): Resource<Flow<List<String>>, Flow<List<Int>>>  {
        var flowListTipoId: Flow<List<Int>> = flowOf()

        val flowListTipoDesc = tipoDao.buscaTipoDesc()
        if (descricao.isNotEmpty()){
            flowListTipoId = tipoDao.buscaTipoId(descricao)
        }

        return Resource(
            nomes = flowListTipoDesc,
            ids = flowListTipoId
        )
    }

    override fun buscaNivel(descricao: String): Resource<Flow<List<String>>, Flow<List<Int>>> {
        var flowListNivelId: Flow<List<Int>> = flowOf()

        val flowListNivelDesc = nivelDao.buscaNivelDesc()

        if (descricao.isNotEmpty()){
            flowListNivelId = nivelDao.buscaNivelId(descricao)
        }

        return Resource(
            nomes = flowListNivelDesc,
            ids = flowListNivelId
        )
    }

}