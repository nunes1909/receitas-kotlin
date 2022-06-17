package com.example.receitas.domain.useCase.buscaTipoNivel

import com.example.receitas.data.repository.formulario.FormularioRepository

class BuscaIdPelaDescricao(
    private val repository: FormularioRepository
) : BuscaIdPelaDescricaoUseCase {
    override suspend fun buscaTipoId(descricao: String): Int {
        return repository.buscaTipoIdPelaDescricao(descricao = descricao)
    }

    override suspend fun buscaNivelId(descricao: String): Int {
        return repository.buscaNivelIdPelaDescricao(descricao)
    }
}