package com.example.receitas.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitas.domain.model.Receita
import com.example.receitas.domain.useCase.buscaReceita.BuscaReceitaPorIdUseCase
import com.example.receitas.domain.useCase.carregaFormulario.BuscaTodosNiveisUseCase
import com.example.receitas.domain.useCase.carregaFormulario.BuscaTodosTiposUseCase
import com.example.receitas.domain.useCase.criaReceita.SalvaReceitaUseCase
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
    private var _criaReceita = MutableLiveData<Boolean>()
    val criaReceita = _criaReceita as LiveData<Boolean>

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

            var tipo = ""
            var nivel = ""
            tipo = when (receita.tipoId) {
                1 -> "Refeição"
                2 -> "Lanche"
                else -> "Drink"
            }
            nivel = when (receita.nivelId) {
                1 -> "Fácil"
                2 -> "Médio"
                else -> "Difícil"
            }

            val receitaPresenter = PresenterReceita(
                id = receita.id,
                titulo = receita.titulo,
                tipoId = tipo,
                nivelId = nivel,
                ingredientes = receita.ingredientes,
                preparo = receita.preparo
            )
            val flowTipoReceita = buscaTodosTiposUseCase("")
            val flowNivelReceita = buscaTodosNiveisUseCase("")

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

        if (id == 0L) {
            val saveReceita = Receita(
                titulo = titulo,
                tipoId = tipoId,
                nivelId = nivelId,
                ingredientes = ingredientes,
                preparo = preparo
            )
            _criaReceita.postValue(salvaReceitaUseCase(saveReceita))

        } else {
            val editReceita = Receita(
                id = id,
                titulo = titulo,
                tipoId = tipoId,
                nivelId = nivelId,
                ingredientes = ingredientes,
                preparo = preparo
            )
            _criaReceita.postValue(salvaReceitaUseCase(editReceita))

        }


    }

    private fun validaNivel(nivel: String?): Int? {
        var nivelId: Int? = null
        when (nivel) {
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
