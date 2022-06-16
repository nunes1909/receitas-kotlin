package com.example.receitas.domain.useCase.buscaTipoNivel

interface BuscaDescricaoPeloIdUseCase {
    suspend fun buscaTipoDescricao(id: Int): String
    suspend fun buscaNivelDescricao(id: Int): String
}
