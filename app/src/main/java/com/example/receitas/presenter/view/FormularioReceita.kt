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
        lifecycleScope.launch {
            carregaFormulario()
        }
        observers()
    }

    private fun carregaFormulario() {
        viewModel.carregaFormulario()
    }

    private fun observers() {
        nivelObserver()
        tipoObserver()
        saveObserver()
    }

    private fun saveObserver() {
        viewModel.salva.observe(this@FormularioReceita) { ifSave ->
            ifSave?.let {
                finish()
            }
        }
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_receita, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_action_save) {
            lifecycleScope.launch {
                salva()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private suspend fun salva() {
        val titulo = binding.formularioReceitaTitulo.text.toString().trim()
//        val tipo = binding.formularioReceitaTipo.text.toString()
//        val nivel = binding.formularioReceitaNivel.text.toString()
        val ingredientes = binding.formularioReceitaIngrediente.text.toString().trim()
        val preparo = binding.formularioReceitaPreparo.text.toString()


        binding.formularioReceitaNivel.text.toString().trim()

        viewModel.salva(
            preparo = preparo,
            id = null,
            titulo = titulo,
            tipo = 2,
            nivel = 2,
            ingredientes = ingredientes
        )


    }
}