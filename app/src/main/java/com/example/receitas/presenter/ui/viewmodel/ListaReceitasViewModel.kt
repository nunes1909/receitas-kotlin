package com.example.receitas.presenter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase
import kotlinx.coroutines.launch

class ListaReceitasViewModel(
    private val buscaTodasReceitasUseCase: BuscaTodasReceitasUseCase
) : ViewModel() {

    /**
     * LiveData que busca todas as receitas no Banco
     */
    private var _mBusca = MutableLiveData<List<Receita>>()
    val busca = _mBusca as LiveData<List<Receita>>

    suspend fun buscaReceitas() {
        val flowReceitas = buscaTodasReceitasUseCase()

        viewModelScope.launch {
            flowReceitas.collect { listReceitas ->
                _mBusca.value = listReceitas
            }
        }
    }
}