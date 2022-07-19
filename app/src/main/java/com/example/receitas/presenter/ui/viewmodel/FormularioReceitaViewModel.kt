package com.example.receitas.presenter.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitas.domain.useCase.buscaReceita.BuscaReceitaPorIdUseCase
import com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario.BuscaTodosNiveisUseCase
import com.example.receitas.domain.useCase.buscaTipoNivel.carregaFormulario.BuscaTodosTiposUseCase
import com.example.receitas.domain.useCase.criaReceita.SalvaReceitaUseCase
import com.example.receitas.domain.useCase.deletaReceita.DeletaReceitaUseCase
import com.example.receitas.presenter.mapper.PresenterMapper
import com.example.receitas.presenter.mapper.ReceitaMapper
import com.example.receitas.presenter.mapper.ResourceReceita
import com.example.receitas.presenter.model.ReceitaPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormularioReceitaViewModel(
    private val receitaMapper: ReceitaMapper,
    private val salvaReceitaUseCase: SalvaReceitaUseCase,
    private val buscaReceitaPorIdUseCase: BuscaReceitaPorIdUseCase,
    private val deletaReceitaUseCase: DeletaReceitaUseCase,
    private val buscaTodosTiposUseCase: BuscaTodosTiposUseCase,
    private val buscaTodosNiveisUseCase: BuscaTodosNiveisUseCase,
    private val presenterMapper: PresenterMapper
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
     * Neste LiveData existe uma transformação de model (domain para presenter)
     */
    private var _buscaReceitaPorId = MutableLiveData<ResourceReceita>()
    val buscaReceitaPorId = _buscaReceitaPorId as LiveData<ResourceReceita>

    suspend fun buscaPorId(id: Long) {
        if (id > 0L && id != null) {
            val receita = buscaReceitaPorIdUseCase(id = id)

            val receitaPresenter = presenterMapper.paraPresenter(receita)

            val idTipoNivel = presenterMapper.buscaIdTipoNivel(
                nivel = receitaPresenter.nivel,
                tipo = receitaPresenter.tipo
            )

            _buscaReceitaPorId.postValue(
                ResourceReceita(
                    presenterReceita = receitaPresenter,
                    tipoNivelPresenter = idTipoNivel
                )
            )
        }
    }


    /**
     * LiveData para Salvar a receita
     * Neste LiveData existe uma transformação de model (presenter para domain)
     */
    private var _salvaReceita = MutableLiveData<Boolean>()
    val salvaReceita = _salvaReceita as LiveData<Boolean>

    private var validacao = false

    suspend fun salvaReceita(receita: ReceitaPresenter) {
        validacao = true

        _validaTitulo.value = validaCampos(receita.titulo)
        _validaTipo.value = validaCampos(receita.tipo)
        _validaNivel.value = validaCampos(receita.nivel)

        if (validacao) {
            val receitaDomain = presenterMapper.paraDomain(receita)
            _salvaReceita.value = salvaReceitaUseCase(receitaDomain)
        }
    }

    /**
     * LiveDatas das validações dos campos obrigatórios no formulário
     */
    private var _validaTitulo = MutableLiveData<Boolean>()
    val validaTitulo = _validaTitulo as LiveData<Boolean>

    private var _validaTipo = MutableLiveData<Boolean>()
    val validaTipo = _validaTipo as LiveData<Boolean>

    private var _validaNivel = MutableLiveData<Boolean>()
    val validaNivel = _validaNivel as LiveData<Boolean>

    private fun validaCampos(valor: String): Boolean {
        return if (valor.isNotEmpty()) {
            true
        } else {
            validacao = false
            false
        }
    }

    /**
     * LiveData que carrega a foto na imageview do formulário
     */
    private var _mCarregaFoto = MutableLiveData<Bitmap>()
    val carregaFoto = _mCarregaFoto as LiveData<Bitmap>

    fun carregaImagem(imageString: Bitmap) {
        _mCarregaFoto.value = imageString
    }

    /**
     * LiveData para remover uma receita
     * Neste LiveData existe uma transformação de model (presenter para domain)
     */
    private var _mRemoveReceita = MutableLiveData<Boolean>()

    val mRemoveReceita = _mRemoveReceita as LiveData<Boolean>

    suspend fun removeReceita(receita: ReceitaPresenter) {
        val receitaFormatada = receitaMapper.dePresenterParaDomain(receita)
        val idReceita = receitaFormatada.id
        _mRemoveReceita.postValue(deletaReceitaUseCase(id = idReceita))
    }

    /**
     * LiveData que limpa os dados do formulário
     */
    private val _mLimpaForm = MutableLiveData<Boolean>()
    val limpaForm = _mLimpaForm as LiveData<Boolean>

    fun limpaFormulario() {
        _mLimpaForm.value = true
    }

}
