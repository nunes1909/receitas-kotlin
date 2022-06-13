package com.example.receitas.presenter.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.receitas.R
import com.example.receitas.databinding.FormularioReceitaBinding
import com.example.receitas.domain.model.Receita
import com.example.receitas.presenter.viewmodel.FormularioReceitaViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormularioReceita : AppCompatActivity() {

    private val binding by lazy {
        FormularioReceitaBinding.inflate(layoutInflater)
    }
    private var receitaId = 0L
    private var dataReceitaEdit: Receita? = null
    private val viewModel: FormularioReceitaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarId()
        observers()

        lifecycleScope.launch {
            configuraFormulario()
        }

    }

    /**
     * Busca tipos e niveis no banco
     * Tenta buscar uma receita pelo Id
     */
    private suspend fun configuraFormulario() {
        viewModel.configuraFormulario()
        viewModel.buscaPorId(receitaId)
    }

    fun observers() {
        // Observa o dropDown Tipo
        viewModel.buscaTipo.observe(this@FormularioReceita) { listTipo ->
            val tipoAdapter = ArrayAdapter(this@FormularioReceita, R.layout.drop_values_receita, listTipo)
            binding.formularioReceitaTipo.setAdapter(tipoAdapter)
        }

        // Observa o dropDown Nivel
        viewModel.buscaNivel.observe(this@FormularioReceita) { listNivel ->
            val nivelAdapter =
                ArrayAdapter(this@FormularioReceita, R.layout.drop_values_receita, listNivel)
            binding.formularioReceitaNivel.setAdapter(nivelAdapter)
        }

        // Observa a criação da receita
        viewModel.criaReceita.observe(this@FormularioReceita) { ifSave ->
            if (ifSave) finish()
            else Snackbar.make(
                binding.root, "Erro ao cadastrar a receita",
                Snackbar.LENGTH_LONG
            ).show()
        }

//        viewModel.buscaReceitaPorId.observe(this@FormularioReceita){ receita ->
//            with(binding){
//                formularioReceitaTitulo.setText(receita.titulo)
//                formularioReceitaTipo.setText(receita.)
//            }
//        }

    }


    private fun tentaCarregarId() {
        val idRecebido = intent.getLongExtra("receita_id", 0L)
        if (idRecebido > 0L && idRecebido != null){
            receitaId = idRecebido
        }
    }

    private fun finalizaFormulario() {
        val titulo = binding.formularioReceitaTitulo.text.toString().trim()
        val tipo = binding.formularioReceitaTipo.text.toString()
        val nivel = binding.formularioReceitaNivel.text.toString()

        val ingredientes = binding.formularioReceitaIngrediente.text.toString().trim()
        val preparo = binding.formularioReceitaPreparo.text.toString()

        lifecycleScope.launch {
            viewModel.salvaReceita(
                id = receitaId,
                titulo = titulo,
                tipo = tipo,
                nivel = nivel,
                ingredientes = ingredientes,
                preparo = preparo
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_receita, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_action_save) {
            finalizaFormulario()
        }
        return super.onOptionsItemSelected(item)
    }
}
