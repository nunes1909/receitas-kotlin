package com.example.receitas.domain.useCase.buscaReceita

import android.util.Log
import com.example.receitas.data.repository.RepositoryDataSource
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * AQUI DEVE SER FEITO O DELEGATE DE BUSCAR TODAS AS RECEITAS PARA O REPOSITORY
 */

class BuscaTodasReceitas(
    private val repository: RepositoryDataSource
) : BuscaTodasReceitasUseCase {
    override fun invoke(): Flow<List<Receita>?> {
        return try {
           repository.buscaTodasReceitas()
        } catch (e: Exception){
            Log.e("BuscaTodasReceitas", "BuscaTodasReceitas: $e")
            flowOf()
        }
    }
}