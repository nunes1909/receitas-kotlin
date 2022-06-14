package com.example.receitas.presenter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase
import com.example.receitas.domain.useCase.deletaReceita.DeletaTodasReceitasUseCase
import kotlinx.coroutines.launch

class ListaReceitasViewModel(
    private val buscaTodasReceitasUseCase: BuscaTodasReceitasUseCase,
    private val deletaTodasReceitasUseCase: DeletaTodasReceitasUseCase
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

    /**
     * LiveData que remove todas as receitas
     */
    private var _mDeletaTodas = MutableLiveData<Boolean>()
    val deleta = _mDeletaTodas as LiveData<Boolean>

    suspend fun deletaTodas(){
        _mDeletaTodas.postValue(deletaTodasReceitasUseCase())
    }
}