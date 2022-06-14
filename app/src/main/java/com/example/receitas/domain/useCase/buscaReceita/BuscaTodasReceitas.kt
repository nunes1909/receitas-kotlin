package com.example.receitas.domain.useCase.buscaReceita

import android.util.Log
import com.example.receitas.data.repository.ReceitasRepository
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Implementação do caso de uso que busca todas as receitas
 */

class BuscaTodasReceitas(
    private val repository: ReceitasRepository
) : BuscaTodasReceitasUseCase {
    override fun invoke(): Flow<List<Receita>> {
        return try {
            repository.buscaTodasReceitas()
        } catch (e: Exception) {
            Log.e("BuscaTodasReceitas", "BuscaTodasReceitas: $e")
            flowOf()
        }
    }
}