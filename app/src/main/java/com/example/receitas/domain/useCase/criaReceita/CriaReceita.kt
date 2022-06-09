package com.example.receitas.domain.useCase.criaReceita

import android.util.Log
import com.example.receitas.data.repository.RepositoryDataSource
import com.example.receitas.domain.model.Receita

/**
 * AQUI DEVE SER FEITO O DELEGATE DE CRIA RECEITA PARA O REPOSITORY
 */

class CriaReceita(
    private val repository: RepositoryDataSource
): CriaReceitaUseCase {
    override suspend fun invoke(receita: Receita): Boolean {
        return try {
            repository.salvaReceita(receita = receita)
            true
        } catch (e: Exception){
            Log.e("DOMAIN_TAG", "CriaReceita: $e")
            false
        }
    }
}