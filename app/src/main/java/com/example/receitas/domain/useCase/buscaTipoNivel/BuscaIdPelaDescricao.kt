package com.example.receitas.domain.useCase.buscaTipoNivel

import com.example.receitas.data.repository.FormularioRepositoryImpl

class BuscaIdPelaDescricao(
    private val repository: FormularioRepositoryImpl
) : BuscaIdPelaDescricaoUseCase {
    override suspend fun buscaTipoId(descricao: String): Int {
        return repository.buscaTipoIdPelaDescricao(descricao = descricao)
    }

    override suspend fun buscaNivelId(descricao: String): Int {
        return repository.buscaNivelIdPelaDescricao(descricao)
    }
}