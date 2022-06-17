package com.example.receitas.data.repository

import android.util.Log
import com.example.receitas.data.database.dao.NivelReceitaDao
import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.data.database.dao.TipoReceitaDao
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Repository de receitas implementando a interface de busca DataSource
 */

class ReceitasRepository(
    private val receitaDao: ReceitaDao,
    private val tipoDao: TipoReceitaDao,
    private val nivelDao: NivelReceitaDao
) : ReceitasDataSource {

    // Salva receita
    override suspend fun salvaReceita(receita: Receita): Boolean {
        val save: Boolean = try {
            if (receita.id > 0) {
                receitaDao.edita(receita)
            } else {
                receitaDao.salva(receita)
            }
            true
        } catch (e: Exception) {
            Log.e("TAG", "salvaReceita: $e")
            false
        }
        return save
    }

    // Busca todas as receitas
    override fun buscaTodasReceitas(valor: String): Flow<List<Receita>> {
        return when (valor) {
            "tipo" -> receitaDao.reorderTipo()
            "nivel" -> receitaDao.reorderNivel()
            "asc" -> receitaDao.reorderIdCrescente()
            "desc" -> receitaDao.reorderIdDecrescente()
            else -> flowOf()
        }
    }

    // Busca receita pelo Id
    override suspend fun buscaReceitaPorId(id: Long): Receita {
        return try {
            receitaDao.buscaReceitaPorId(id)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun deletaReceita(id: Long): Boolean {
        return try {
            receitaDao.deleta(id)
            true
        } catch (e: Exception) {
            throw e
            false
        }
    }

    override suspend fun removeTodasReceitas(): Boolean {
        return try {
            receitaDao.deletaTodas()
            true
        } catch (e: Exception) {
            throw e
            false
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

    override suspend fun buscaTipoIdPelaDescricao(descricao: String): Int {
        return tipoDao.buscaId(descricao)
    }

    override suspend fun buscaTipoDescricaoPeloId(id: Int): String {
        return tipoDao.buscaDescricao(id)
    }

    override suspend fun buscaNivelIdPelaDescricao(descricao: String): Int {
        return nivelDao.buscaId(descricao)
    }

    override suspend fun buscaNivelDescricaoPeloId(id: Int): String {
        return nivelDao.buscaDescricao(id)
    }
}