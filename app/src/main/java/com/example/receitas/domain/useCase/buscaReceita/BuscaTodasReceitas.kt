package com.example.receitas.domain.useCase.buscaReceita

import android.util.Log
import com.example.receitas.data.repository.receitas.ReceitasRepository
import com.example.receitas.domain.model.Receita
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BuscaTodasReceitas(
    private val repository: ReceitasRepository
) : BuscaTodasReceitasUseCase {
    override fun invoke(valor: String): Flow<List<Receita>> {
        return try {
            repository.buscaTodasReceitas(valor)
        } catch (e: Exception) {
            Log.e("BuscaTodasReceitas", "BuscaTodasReceitas: $e")
            flowOf()
        }
    }
}