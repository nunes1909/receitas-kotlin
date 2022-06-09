package com.example.receitas.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.receitas.databinding.ListaReceitasBinding
import com.example.receitas.presenter.adapter.ReceitasAdapter

class ListaReceitas : AppCompatActivity() {

    private val binding by lazy {
        ListaReceitasBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        ReceitasAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
        configuraRV()
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