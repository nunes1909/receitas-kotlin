package com.example.receitas.domain.useCase.criaReceita

import com.example.receitas.domain.model.Receita

/**
 * Caso de uso de salvar a receita
 */
interface SalvaReceitaUseCase {
    suspend operator fun invoke(receita: Receita): Boolean
}