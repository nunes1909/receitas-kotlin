package com.example.receitas.presenter.ui.view

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.imageLoader
import com.example.receitas.R
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
        adapter.listener = { id: Long ->
            Intent(this@ListaReceitas, FormularioReceita::class.java).apply {
                putExtra("receita_id", id)
                startActivity(this)
            }
        }
    }

    private fun observers() {
        viewModel.busca.observe(this@ListaReceitas) { listaDeReceitas ->
            adapter.atualiza(listaDeReceitas)
        }

        viewModel.deleta.observe(this@ListaReceitas) { ifDeleteAll ->
            if (ifDeleteAll) adapter.notifyDataSetChanged()
        }
    }

    private suspend fun buscaTodas() {
        viewModel.buscaReceitas("desc")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_lista_receitas, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_action_delete_all -> removeTodasReceitas()
            R.id.ic_order_tipo -> reorderReceitas("tipo")
            R.id.ic_order_nivel -> reorderReceitas("nivel")
            R.id.ic_order_id_asc -> reorderReceitas("asc")
            R.id.ic_order_id_desc -> reorderReceitas("desc")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeTodasReceitas() {
        AlertDialog.Builder(this@ListaReceitas)
            .setIcon(R.drawable.ic_action_warning)
            .setTitle("Apagar todas as receitas?")
            .setPositiveButton("Sim") { _, _ ->
                lifecycleScope.launch {
                    viewModel.deletaTodas()
                }
            }
            .setNegativeButton("Não") { _, _ -> }
            .show()
    }

    private fun reorderReceitas(item: String) {
        lifecycleScope.launch {
            viewModel.buscaReceitas(item)
        }
    }
}