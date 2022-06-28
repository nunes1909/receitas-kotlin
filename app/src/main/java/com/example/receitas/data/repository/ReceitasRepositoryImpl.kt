package com.example.receitas.data.repository

import android.util.Log
import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.repository.ReceitasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ReceitasRepositoryImpl(
    private val receitaDao: ReceitaDao
) : ReceitasRepository {
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

    // Remove uma receita pelo id
    override suspend fun deletaReceita(id: Long): Boolean {
        return try {
            receitaDao.deleta(id)
            true
        } catch (e: Exception) {
            throw e
            false
        }
    }

    // Remove todas as receitas
    override suspend fun removeTodasReceitas(): Boolean {
        return try {
            receitaDao.deletaTodas()
            true
        } catch (e: Exception) {
            throw e
            false
        }
    }
}