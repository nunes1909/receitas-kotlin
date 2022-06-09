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
        carregaFormulario()
        lifecycleScope.launch {
            observers()
        }

        val titulo = binding.formularioReceitaTitulo.text.toString().trim()
        val ingredientes = binding.formularioReceitaIngrediente.text.toString().trim()
        val preparo = binding.formularioReceitaPreparo.text.toString()

    }

    private suspend fun observers() {
        nivelObserver()
        tipoObserver()
    }

    private fun tipoObserver() {
        viewModel.buscaTipo.observe(this@FormularioReceita) { listTipo ->
            val arreyTipo =
                ArrayAdapter(this@FormularioReceita, R.layout.drop_tipo_receita, listTipo)
            binding.formularioReceitaTipo.setAdapter(arreyTipo)
        }
    }

    private fun nivelObserver() {
        viewModel.buscaNivel.observe(this@FormularioReceita) { listNivel ->
            val arrayNivel =
                ArrayAdapter(this@FormularioReceita, R.layout.drop_nivel_receita, listNivel)
            binding.formularioReceitaNivel.setAdapter(arrayNivel)
        }
    }

    private fun carregaFormulario() {
        viewModel.carregaFormulario()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_receita, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finalizaFormulario(item)
        return super.onOptionsItemSelected(item)
    }

    private fun finalizaFormulario(item: MenuItem) {
        when (item.itemId) {
            R.id.ic_action_save -> {

            }
        }
    }
}