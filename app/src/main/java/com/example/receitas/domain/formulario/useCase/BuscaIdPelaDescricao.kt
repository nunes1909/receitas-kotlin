package com.example.receitas.domain.formulario.useCase

import com.example.receitas.data.formulario.repository.FormularioRepositoryImpl

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