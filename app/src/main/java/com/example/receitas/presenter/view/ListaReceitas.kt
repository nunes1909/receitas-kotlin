package com.example.receitas.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.receitas.databinding.ListaReceitasBinding
import com.example.receitas.presenter.adapter.ReceitasAdapter
import com.example.receitas.presenter.viewmodel.ListaReceitasViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaReceitas : AppCompatActivity() {

    private val binding by lazy {
        ListaReceitasBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        ReceitasAdapter()
    }
    private val viewModel: ListaReceitasViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
        configuraRV()
        criaReceiaTeste()
        buscaTodas()
        observers()
    }

    private fun buscaTodas() {
        lifecycleScope.launch{
            viewModel.buscaReceitas()
        }
    }

    private fun observers() {
        viewModel.busca.observe(this@ListaReceitas){ listaDeReceitas ->
            adapter.atualiza(listaDeReceitas)
        }
    }

    private fun criaReceiaTeste() {
        //        lifecycleScope.launch {
        //            ReceitaDatabase.getInstance(this@ListaReceitas).receitaDao().apply {
        //                val receita = Receita(
        //                    titulo = "Teste 1",
        //                    ingredientes = "Teste 2",
        //                    preparo = "Teste 3",
        //                    tipoId = 1,
        //                    nivelId = 3
        //                )
        //                this.salva(receita)
        //            }
        //        }
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
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }
}