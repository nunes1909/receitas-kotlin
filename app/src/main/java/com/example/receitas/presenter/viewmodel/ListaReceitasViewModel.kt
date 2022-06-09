package com.example.receitas.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase

class ListaReceitasViewModel(
    private val buscaTodasReceitasUseCase: BuscaTodasReceitasUseCase
): ViewModel() {

}