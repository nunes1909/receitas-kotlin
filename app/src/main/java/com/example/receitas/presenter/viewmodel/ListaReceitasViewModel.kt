package com.example.receitas.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class ListaReceitasViewModel(
    private val buscaTodasReceitasUseCase: BuscaTodasReceitasUseCase
) : ViewModel() {

    private val _mBusca = MutableLiveData<List<Receita>>()
    val busca = _mBusca as LiveData<List<Receita>>

    val flowReceitas = buscaTodasReceitasUseCase()
    suspend fun buscaReceitas() {
        if (flowReceitas != null) {
            flowReceitas.collect { listReceitas ->
                _mBusca.value = listReceitas
            }
        }
    }
}