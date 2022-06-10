package com.example.receitas.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitas.domain.model.NivelReceita
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.model.TipoReceita
import com.example.receitas.domain.useCase.carregaFormulario.BuscaNivelUseCase
import com.example.receitas.domain.useCase.carregaFormulario.BuscaTipoUseCase
import com.example.receitas.domain.useCase.criaReceita.CriaReceitaUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FormularioReceitaViewModel(
    private val criaReceitaUseCase: CriaReceitaUseCase,
    private val buscaTipoUseCase: BuscaTipoUseCase,
    private val buscaNivelUseCase: BuscaNivelUseCase
) : ViewModel() {

    private var _mBuscaTipo = MutableLiveData<List<TipoReceita>>()
    val buscaTipo = _mBuscaTipo as LiveData<List<TipoReceita>>

    private var _mBuscaNivel = MutableLiveData<List<NivelReceita>>()
    val buscaNivel = _mBuscaNivel as LiveData<List<NivelReceita>>

    private var _mSalva = MutableLiveData<Boolean>()
    val salva = _mSalva as LiveData<Boolean>

    suspend fun salva(
        id: Long?,
        titulo: String,
        tipo: Int?,
        nivel: Int?,
        ingredientes: String,
        preparo: String?
    ) {
        val receita = Receita(
            titulo = titulo,
            tipoId = tipo,
            nivelId = nivel,
            ingredientes = ingredientes,
            preparo = preparo
        )
        _mSalva.value = criaReceitaUseCase(receita)
    }

    fun carregaFormulario() {
        buscaTipoReceita()
        buscaNivelReceita()
    }

    private fun buscaNivelReceita() {
        val flow = buscaNivelUseCase()

        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            flow.collect{
                _mBuscaNivel.value = it
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