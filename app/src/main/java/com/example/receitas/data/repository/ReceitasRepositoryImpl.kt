package com.example.receitas.data.repository

import android.util.Log
import com.example.receitas.data.database.dao.ReceitaDao
import com.example.receitas.data.mapper.DataMapper
import com.example.receitas.data.model.Receita
import com.example.receitas.domain.model.ReceitaDomain
import com.example.receitas.domain.repository.ReceitasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ReceitasRepositoryImpl(
    private val receitaDao: ReceitaDao,
    private val dataMapper: DataMapper
) : ReceitasRepository {
    // Salva receita
    override suspend fun salvaReceita(receita: ReceitaDomain): Boolean {
        val save: Boolean = try {
            val receitaData = dataMapper.paraData(receita)

            if (receita.id > 0) {
                receitaDao.edita(receitaData)
            } else {
                receitaDao.salva(receitaData)
            }

            true
        } catch (e: Exception) {
            Log.e("TAG", "salvaReceita: $e")
            false
        }

        return save
    }

    // Busca todas as receitas
    override suspend fun buscaTodasReceitas(valor: String): Flow<List<ReceitaDomain>> {
        return try {
            when (valor) {
                "nivel_asc" -> {
                    val flowNiveAsc = receitaDao.reorderNivelAsc()
                    dataMapper.paraFlowDomain(flowNiveAsc)
                }
                "nivel_desc" -> {
                    val flowNivelDesc = receitaDao.reorderNivelDesc()
                    dataMapper.paraFlowDomain(flowNivelDesc)
                }
                "asc" -> {
                    val flowAsc = receitaDao.reorderIdCrescente()
                    dataMapper.paraFlowDomain(flowAsc)
                }
                "desc" -> {
                    val flowDesc = receitaDao.reorderIdDecrescente()
                    dataMapper.paraFlowDomain(flowDesc)
                }
                else -> flowOf()
            }
        } catch (e: Exception) {
            Log.e("BuscaTodasReceitas", "Erro ao buscar as receitas: $e")
            flowOf()
        }
    }

    // Busca receita pelo Id
    override suspend fun buscaReceitaPorId(id: Long): ReceitaDomain {
        return try {
            val receita = receitaDao.buscaReceitaPorId(id)
            dataMapper.paraDomain(receita)
        } catch (e: Exception) {
            Log.e("BuscaReceitaPorId", "Erro ao buscar a receita pelo ID $e")
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