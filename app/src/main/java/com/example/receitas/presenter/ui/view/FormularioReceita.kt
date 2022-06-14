package com.example.receitas.presenter.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.receitas.R
import com.example.receitas.databinding.FormularioReceitaBinding
import com.example.receitas.presenter.model.PresenterReceita
import com.example.receitas.presenter.ui.viewmodel.FormularioReceitaViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormularioReceita : AppCompatActivity() {

    private val binding by lazy {
        FormularioReceitaBinding.inflate(layoutInflater)
    }
    private var receitaId = 0L
    private var presenterReceita: PresenterReceita? = null
    private val viewModel: FormularioReceitaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarId()
        lifecycleScope.launch {
            configuraFormulario()
        }

        observers()

    }

    private fun tentaCarregarId() {
        val idRecebido = intent.getLongExtra("receita_id", 0L)
        receitaId = idRecebido
    }

    private suspend fun configuraFormulario() {
        viewModel.configuraFormulario()
        viewModel.buscaPorId(receitaId)
    }

    fun observers() {
        // Observa o dropDown Tipo
        viewModel.buscaTipo.observe(this@FormularioReceita) { listTipo ->
            val tipoAdapter =
                ArrayAdapter(this@FormularioReceita, R.layout.drop_values_receita, listTipo)
            binding.formularioReceitaTipo.setAdapter(tipoAdapter)
        }

        // Observa o dropDown Nivel
        viewModel.buscaNivel.observe(this@FormularioReceita) { listNivel ->
            val nivelAdapter =
                ArrayAdapter(this@FormularioReceita, R.layout.drop_values_receita, listNivel)
            binding.formularioReceitaNivel.setAdapter(nivelAdapter)
        }

        // Observa a criação da receita
        viewModel.salvaReceita.observe(this@FormularioReceita) { ifSave ->
            if (ifSave) finish()
        }

        // Observa a busca da receita pelo Id e popula os campos
        viewModel.buscaReceitaPorId.observe(this@FormularioReceita) { receita ->
            binding.run {
                presenterReceita = receita
                formularioReceitaTitulo.setText(receita.titulo)
                formularioReceitaIngrediente.setText(receita.ingredientes)
                formularioReceitaPreparo.setText(receita.preparo)
            }
        }

        // Observa
        viewModel.mRemoveReceita.observe(this@FormularioReceita) { ifDelete ->
            if (ifDelete) finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_receita, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_action_save -> {
                salvaReceita()
            }
            R.id.ic_action_delete -> {
                removeReceita()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun salvaReceita() {
        val titulo = binding.formularioReceitaTitulo.text.toString().trim()
        val tipo = binding.formularioReceitaTipo.text.toString()
        val nivel = binding.formularioReceitaNivel.text.toString()
        val ingredientes = binding.formularioReceitaIngrediente.text.toString().trim()
        val preparo = binding.formularioReceitaPreparo.text.toString()

        lifecycleScope.launch {
            viewModel.salvaReceita(

                PresenterReceita(
                    id = receitaId,
                    titulo = titulo,
                    tipoId = tipo,
                    nivelId = nivel,
                    ingredientes = ingredientes,
                    preparo = preparo
                )
            )
        }
    }

    private fun removeReceita() {
        lifecycleScope.launch {
            presenterReceita?.let { receita ->
                AlertDialog.Builder(this@FormularioReceita)
                    .setTitle("Excluíndo receita")
                    .setMessage("Tem certeza que quer remover essa receita?")
                    .setPositiveButton("Sim") { _, _ ->
                        launch {
                            viewModel.removeReceita(receita)
                        }
                    }
                    .setNegativeButton("Não") { _, _ -> }
                    .show()
            }
        }
    }
}
