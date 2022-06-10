package com.example.receitas.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitas.domain.model.NivelReceita
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.useCase.carregaFormulario.BuscaNivelUseCase
import com.example.receitas.domain.useCase.carregaFormulario.BuscaTipoUseCase
import com.example.receitas.domain.useCase.criaReceita.CriaReceitaUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormularioReceitaViewModel(
    private val criaReceitaUseCase: CriaReceitaUseCase,
    private val buscaTipoUseCase: BuscaTipoUseCase,
    private val buscaNivelUseCase: BuscaNivelUseCase
) : ViewModel() {

    private var _mBuscaTipo = MutableLiveData<List<String>>()
    val buscaTipo = _mBuscaTipo as LiveData<List<String>>

    private var _mBuscaNivel = MutableLiveData<List<String>>()
    val buscaNivel = _mBuscaNivel as LiveData<List<String>>

    private var _criaReceita = MutableLiveData<Boolean>()
    val criaReceita = _criaReceita as LiveData<Boolean>

    fun configuraFormulario() {
        val flowTipoReceita = buscaTipoUseCase()
        val flowNivelReceita = buscaNivelUseCase()

        CoroutineScope(Dispatchers.IO).launch {
            flowTipoReceita.collect { listTipo ->
                _mBuscaTipo.postValue(listTipo)
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            flowNivelReceita.collect { listNivel ->
                _mBuscaNivel.postValue(listNivel)
            }
        }
    }

    suspend fun salvaReceita(
        id: Long?,
        titulo: String,
        tipo: String?,
        nivel: String?,
        ingredientes: String,
        preparo: String?
    ) {
        val tipoId: Int? = validaTipoId(tipo)
        val nivelId: Int? = validaNivel(nivel)


        if (id == null){
            val criandoReceita = Receita(
                titulo = titulo,
                tipoId = tipoId,
                nivelId = nivelId,
                ingredientes = ingredientes,
                preparo = preparo
            )
            _criaReceita.postValue(criaReceitaUseCase(criandoReceita))
        }
    }

    private fun validaNivel(nivel: String?): Int? {
        var nivelId: Int? = null
        when(nivel){
            "Fácil" -> nivelId = 1
            "Médio" -> nivelId = 2
            "Difícil" -> nivelId = 3
            else -> null
        }
        return nivelId
    }

    private fun validaTipoId(tipo: String?): Int? {
        var tipoId: Int? = null
        when (tipo) {
            "Refeição" -> tipoId = 1
            "Lanche" -> tipoId = 2
            "Drink" -> tipoId = 3
            else -> null
        }
        return tipoId
    }

}