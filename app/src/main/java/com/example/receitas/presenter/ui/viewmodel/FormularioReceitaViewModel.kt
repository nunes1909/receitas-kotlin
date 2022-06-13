package com.example.receitas.presenter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitas.domain.useCase.buscaReceita.BuscaReceitaPorIdUseCase
import com.example.receitas.domain.useCase.carregaFormulario.BuscaTodosNiveisUseCase
import com.example.receitas.domain.useCase.carregaFormulario.BuscaTodosTiposUseCase
import com.example.receitas.domain.useCase.criaReceita.SalvaReceitaUseCase
import com.example.receitas.presenter.mapper.ReceitaMapper
import com.example.receitas.presenter.model.PresenterReceita
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormularioReceitaViewModel(
    private val salvaReceitaUseCase: SalvaReceitaUseCase,
    private val buscaReceitaPorIdUseCase: BuscaReceitaPorIdUseCase,
    private val buscaTodosTiposUseCase: BuscaTodosTiposUseCase,
    private val buscaTodosNiveisUseCase: BuscaTodosNiveisUseCase
) : ViewModel() {

    /**
     * LiveDatas de Cria e Edita receita
     */
    private var _salvaReceita = MutableLiveData<Boolean>()
    val salvaReceita = _salvaReceita as LiveData<Boolean>

    suspend fun salvaReceita(receita: PresenterReceita) {
        val receitaFormatada = ReceitaMapper().convertReceita(receita)
        _salvaReceita.value = salvaReceitaUseCase(receitaFormatada)
    }

    private var _editaReceita = MutableLiveData<Boolean>()
    val editaReceita = _editaReceita as LiveData<Boolean>

    /**
     * LiveData de busca receitas
     */
    private var _buscaReceitaPorId = MutableLiveData<PresenterReceita>()
    val buscaReceitaPorId = _buscaReceitaPorId as LiveData<PresenterReceita>

    suspend fun buscaPorId(id: Long) {
        if (id > 0L && id != null) {
            val receita = buscaReceitaPorIdUseCase(id = id)

            var tipo = when (receita.tipoId) {
                1 -> "Refeição"
                2 -> "Lanche"
                3 -> "Drink"
                else -> null
            }
            var nivel = when (receita.nivelId) {
                1 -> "Fácil"
                2 -> "Médio"
                3 -> "Difícil"
                else -> null
            }

            val receitaPresenter = PresenterReceita(
                id = receita.id,
                titulo = receita.titulo,
                tipoId = tipo,
                nivelId = nivel,
                ingredientes = receita.ingredientes,
                preparo = receita.preparo
            )

            _buscaReceitaPorId.postValue(receitaPresenter)
        }
    }

    // Inicio config Tipo e Nivel
    private var _mBuscaTipo = MutableLiveData<List<String>>()
    val buscaTipo = _mBuscaTipo as LiveData<List<String>>

    private var _mBuscaNivel = MutableLiveData<List<String>>()
    val buscaNivel = _mBuscaNivel as LiveData<List<String>>

    suspend fun configuraFormulario() {
        val flowTipoReceita = buscaTodosTiposUseCase("")
        val flowNivelReceita = buscaTodosNiveisUseCase("")

        CoroutineScope(Dispatchers.IO).launch {
            val flowTipoDesc = flowTipoReceita.nomes
            flowTipoDesc.collect { listTipoDesc ->
                _mBuscaTipo.postValue(listTipoDesc)
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val flowNivelDesc = flowNivelReceita.nomes
            flowNivelDesc.collect { listNivelDesc ->
                _mBuscaNivel.postValue(listNivelDesc)
            }
        }
    }
    // Fim config Tipo e Nivel


}
