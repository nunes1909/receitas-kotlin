package com.example.receitas.domain.useCase.buscaTipoNivel

interface BuscaIdPelaDescricaoUseCase {
    suspend fun buscaTipoId(descricao: String): Int
    suspend fun buscaNivelId(descricao: String): Int
}