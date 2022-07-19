package com.example.receitas.domain.formulario.useCase

interface BuscaDescricaoPeloIdUseCase {
    suspend fun buscaTipoDescricao(id: Int): String
    suspend fun buscaNivelDescricao(id: Int): String
}
