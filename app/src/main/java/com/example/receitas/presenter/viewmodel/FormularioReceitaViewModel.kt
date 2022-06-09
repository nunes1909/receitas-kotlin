package com.example.receitas.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitas.domain.model.NivelReceita
import com.example.receitas.domain.model.TipoReceita
import com.example.receitas.domain.useCase.carregaFormulario.BuscaNivelUseCase
import com.example.receitas.domain.useCase.carregaFormulario.BuscaTipoUseCase
import com.example.receitas.domain.useCase.criaReceita.CriaReceitaUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioReceitaViewModel(
    private val criaReceitaUseCase: CriaReceitaUseCase,
    private val buscaTipoUseCase: BuscaTipoUseCase,
    private val buscaNivelUseCase: BuscaNivelUseCase
) : ViewModel() {

    private var _mBuscaTipo = MutableLiveData<List<TipoReceita>>()
    val buscaTipo = _mBuscaTipo as LiveData<List<TipoReceita>>

    private var _mBuscaNivel = MutableLiveData<List<NivelReceita>>()
    val buscaNivel = _mBuscaNivel as LiveData<List<NivelReceita>>

    fun carregaFormulario() {
        buscaTipoReceita()
        buscaNivelReceita()
    }

    private fun buscaNivelReceita() {
        val flowNivel = buscaNivelUseCase()

        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            flowNivel.collect { listNivel ->
                _mBuscaNivel.value = listNivel
            }
        }
    }

    private fun buscaTipoReceita() {
        val flowTipos = buscaTipoUseCase()

        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            flowTipos.collect { listTipo ->
                _mBuscaTipo.value = listTipo
            }
        }

    }

}