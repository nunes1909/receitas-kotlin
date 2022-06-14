package com.example.receitas.domain.useCase.criaReceita

import android.util.Log
import com.example.receitas.data.repository.ReceitasRepository
import com.example.receitas.domain.model.Receita

/**
 * Implementação do caso de uso de salvar a receita
 */

class SalvaReceita(
    private val repository: ReceitasRepository
) : SalvaReceitaUseCase {
    override suspend fun invoke(receita: Receita): Boolean {
        return try {
            repository.salvaReceita(receita = receita)
            true
        } catch (e: Exception) {
            Log.e("DOMAIN_TAG", "CriaReceita: $e")
            false
        }
    }
}