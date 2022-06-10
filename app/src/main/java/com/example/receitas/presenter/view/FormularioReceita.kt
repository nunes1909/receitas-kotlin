package com.example.receitas.presenter.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.receitas.R
import com.example.receitas.databinding.FormularioReceitaBinding
import com.example.receitas.presenter.viewmodel.FormularioReceitaViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormularioReceita : AppCompatActivity() {

    private val binding by lazy {
        FormularioReceitaBinding.inflate(layoutInflater)
    }
    private val viewModel: FormularioReceitaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFormulario()
        observers()
    }

    /**
     * Este metodo configura os dropDowns Tipo e Nivel
     * Deixei em uma funcao separada pois futuramente posso usar a mesma funcao
     *  pra configurar novos comportamentos no formulario além de Tipo e Nivel
     */
    private fun configuraFormulario() {
        viewModel.configuraFormulario()
    }

    fun observers() {
        // Observa o dropDown Tipo
        viewModel.buscaTipo.observe(this@FormularioReceita) {
            val tipoAdapter = ArrayAdapter(this@FormularioReceita, R.layout.drop_values_receita, it)
            binding.formularioReceitaTipo.setAdapter(tipoAdapter)
        }

        // Observa o dropDown Nivel
        viewModel.buscaNivel.observe(this@FormularioReceita) {
            val nivelAdapter =
                ArrayAdapter(this@FormularioReceita, R.layout.drop_values_receita, it)
            binding.formularioReceitaNivel.setAdapter(nivelAdapter)
        }

        viewModel.criaReceita.observe(this@FormularioReceita){ ifSave ->
            if (ifSave) finish()
            else Snackbar.make(binding.root, "Erro ao cadastrar a receita",
                Snackbar.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_receita, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_action_save) {

            val titulo = binding.formularioReceitaTitulo.text.toString().trim()
            val tipo = binding.formularioReceitaTipo.text.toString()
            val nivel = binding.formularioReceitaNivel.text.toString()
            val ingredientes = binding.formularioReceitaIngrediente.text.toString().trim()
            val preparo = binding.formularioReceitaPreparo.text.toString()

            lifecycleScope.launch {
                viewModel.salvaReceita(
                    id = null,
                    titulo = titulo,
                    tipo = tipo,
                    nivel = nivel,
                    ingredientes = ingredientes,
                    preparo = preparo
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

}