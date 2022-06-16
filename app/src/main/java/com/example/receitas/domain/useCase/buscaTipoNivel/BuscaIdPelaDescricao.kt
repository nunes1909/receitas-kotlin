package com.example.receitas.domain.useCase.buscaTipoNivel

import com.example.receitas.data.repository.ReceitasRepository

class BuscaIdPelaDescricao(
    private val repository: ReceitasRepository
): BuscaIdPelaDescricaoUseCase {
    override suspend fun buscaTipoId(descricao: String): Int {
        return repository.buscaTipoIdPelaDescricao(descricao = descricao)
    }

    override suspend fun buscaNivelId(descricao: String): Int {
        return repository.buscaNivelIdPelaDescricao(descricao)
    }
}