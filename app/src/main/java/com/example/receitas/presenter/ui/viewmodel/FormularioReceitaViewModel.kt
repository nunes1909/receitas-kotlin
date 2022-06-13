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
    private val buscaTodosNiveisUseCase: BuscaTodosNiveisUseCase,
    private val receitaMapper: ReceitaMapper
) : ViewModel() {

    /**
     * LiveData + Configs para buscar no Banco e setar os campos Tipo e Nivel
     */
    private var _mBuscaTipo = MutableLiveData<List<String>>()
    val buscaTipo = _mBuscaTipo as LiveData<List<String>>

    private var _mBuscaNivel = MutableLiveData<List<String>>()
    val buscaNivel = _mBuscaNivel as LiveData<List<String>>

    suspend fun configuraFormulario() {
        val flowTipoReceita = buscaTodosTiposUseCase()
        val flowNivelReceita = buscaTodosNiveisUseCase()

        CoroutineScope(Dispatchers.IO).launch {
            flowTipoReceita.collect { listTipoDesc ->
                _mBuscaTipo.postValue(listTipoDesc)
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            flowNivelReceita.collect { listNivelDesc ->
                _mBuscaNivel.postValue(listNivelDesc)
            }
        }
    }

    /**
     * LiveData + Configs para buscar os dados da receita pelo Id
     */
    private var _buscaReceitaPorId = MutableLiveData<PresenterReceita>()
    val buscaReceitaPorId = _buscaReceitaPorId as LiveData<PresenterReceita>

    suspend fun buscaPorId(id: Long) {
        if (id > 0L && id != null) {
            val receita = buscaReceitaPorIdUseCase(id = id)

            val receitaFormatada = receitaMapper.deDomainParaPresenter(receita)

            _buscaReceitaPorId.postValue(receitaFormatada)
        }
    }

    /**
     * LiveData para Salvar a receita
     */
    private var _salvaReceita = MutableLiveData<Boolean>()
    val salvaReceita = _salvaReceita as LiveData<Boolean>

    suspend fun salvaReceita(receita: PresenterReceita) {
        val receitaFormatada = receitaMapper.dePresenterParaDomain(receita)

        _salvaReceita.value = salvaReceitaUseCase(receitaFormatada)
    }


}
