package com.example.receitas.domain.useCase.buscaTipoNivel

import com.example.receitas.data.formulario.repository.FormularioRepositoryImpl

class BuscaDescricaoPeloId(
    private val repository: FormularioRepositoryImpl
) : BuscaDescricaoPeloIdUseCase {
    override suspend fun buscaTipoDescricao(id: Int): String {
        return repository.buscaTipoDescricaoPeloId(id)
    }

    override suspend fun buscaNivelDescricao(id: Int): String {
        return repository.buscaNivelDescricaoPeloId(id)
    }
}