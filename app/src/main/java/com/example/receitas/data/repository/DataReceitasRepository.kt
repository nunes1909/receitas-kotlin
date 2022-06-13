package com.example.receitas.data.repository

import com.example.receitas.data.database.dao.NivelReceitaDao
import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.data.database.dao.TipoReceitaDao
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow

/**
 * Repository de receitas implementando a interface de busca DataSource
 */

class DataReceitasRepository(
    private val receitaDao: ReceitaDao,
    private val tipoDao: TipoReceitaDao,
    private val nivelDao: NivelReceitaDao
) : ReceitasDataSource {

    // Salva receita
    override suspend fun salvaReceita(receita: Receita): Boolean {
        return try {
            if (receita.id > 0) {
                receitaDao.edita(receita)
            } else {
                receitaDao.salva(receita)
            }
            true
        } catch (e: Exception) {
            false
        }

    }

    // Busca todas as receitas
    override fun buscaTodasReceitas(): Flow<List<Receita>> {
        return receitaDao.buscaTodasReceitas()
    }

    // Busca receita pelo Id
    override suspend fun buscaReceitaPorId(id: Long): Receita {
        return try {
            receitaDao.buscaReceitaPorId(id)
        } catch (e: Exception) {
            throw e
        }
    }

    // Busca os tipos de receita
    override fun buscaTipoValues(): Flow<List<String>> {
        return tipoDao.buscaTipos()
    }

    // Busca os niveis de receita
    override fun buscaNivelValues(): Flow<List<String>> {
        return nivelDao.buscaNiveis()
    }

}