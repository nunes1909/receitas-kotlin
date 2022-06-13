package com.example.receitas.presenter.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.receitas.databinding.ListaReceitasBinding
import com.example.receitas.presenter.adapter.ListaReceitasAdapter
import com.example.receitas.presenter.ui.viewmodel.ListaReceitasViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaReceitas : AppCompatActivity() {

    private val binding by lazy {
        ListaReceitasBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        ListaReceitasAdapter()
    }
    private val viewModel: ListaReceitasViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
        configuraRV()
        observers()
        lifecycleScope.launch {
            buscaTodas()
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            buscaTodas()
        }
    }

    private suspend fun buscaTodas() {
        viewModel.buscaReceitas()
    }

    private fun observers() {
        viewModel.busca.observe(this@ListaReceitas) { listaDeReceitas ->
            adapter.atualiza(listaDeReceitas)
        }
    }

    private fun configuraFab() {
        binding.listaReceitasFab.setOnClickListener {
            Intent(this@ListaReceitas, FormularioReceita::class.java).apply {
                startActivity(this@apply)
            }
        }
    }

    private fun configuraRV() {
        val recycler = binding.listaReceitasRv
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        configuraCliqueRecycler()
    }

    private fun configuraCliqueRecycler() {
        adapter.listener = { id ->
            val intent = Intent(this@ListaReceitas, FormularioReceita::class.java).apply {
                putExtra("receita_id", id)
            }
            startActivity(intent)
        }
    }
}