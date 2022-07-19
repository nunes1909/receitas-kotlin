package com.example.receitas.presenter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitas.domain.useCase.buscaReceita.BuscaTodasReceitasUseCase
import com.example.receitas.domain.useCase.deletaReceita.DeletaTodasReceitasUseCase
import com.example.receitas.presenter.mapper.PresenterMapper
import com.example.receitas.presenter.model.ReceitaPresenter

class ListaReceitasViewModel(
    private val buscaTodasReceitasUseCase: BuscaTodasReceitasUseCase,
    private val deletaTodasReceitasUseCase: DeletaTodasReceitasUseCase,
    private val presenterMapper: PresenterMapper
) : ViewModel() {

    /**
     * LiveData que busca todas as receitas no Banco
     */
    private var _mBusca = MutableLiveData<List<ReceitaPresenter>>()
    val busca = _mBusca as LiveData<List<ReceitaPresenter>>

    suspend fun buscaReceitas(item: String) {
        val flowDomain = buscaTodasReceitasUseCase(item)
        val flowPresenter = presenterMapper.paraFlowPresenter(flowDomain)
        flowPresenter.collect{ lista ->
            _mBusca.value = lista
        }
    }

    /**
     * LiveData que remove todas as receitas
     */
    private var _mDeletaTodas = MutableLiveData<Boolean>()
    val deleta = _mDeletaTodas as LiveData<Boolean>

    suspend fun deletaTodas() {
        _mDeletaTodas.postValue(deletaTodasReceitasUseCase())
    }
}