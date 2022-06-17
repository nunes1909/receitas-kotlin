package com.example.receitas.domain.useCase.buscaTipoNivel

import com.example.receitas.data.repository.formulario.FormularioRepository

class BuscaDescricaoPeloId(
    private val repository: FormularioRepository
) : BuscaDescricaoPeloIdUseCase {
    override suspend fun buscaTipoDescricao(id: Int): String {
        return repository.buscaTipoDescricaoPeloId(id)
    }

    override suspend fun buscaNivelDescricao(id: Int): String {
        return repository.buscaNivelDescricaoPeloId(id)
    }
}