package com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario

import com.example.receitas.data.repository.formulario.FormularioRepository
import kotlinx.coroutines.flow.Flow

class BuscaTodosTipos(
    private val repository: FormularioRepository
) : BuscaTodosTiposUseCase {
    override fun invoke(): Flow<List<String>> {
        return repository.buscaTipoValues()
    }
}