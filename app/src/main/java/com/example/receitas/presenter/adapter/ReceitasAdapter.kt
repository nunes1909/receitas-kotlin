package com.example.receitas.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.receitas.R
import com.example.receitas.databinding.ItemRvReceitaBinding
import com.example.receitas.domain.model.Receita

class ReceitasAdapter : RecyclerView.Adapter<ReceitasAdapter.ReceitaViewHolder>() {

    private var receitas: List<Receita> = mutableListOf()

    inner class ReceitaViewHolder(binding: ItemRvReceitaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding = binding

        fun vincula(receita: Receita) {
            val titulo = binding.itemRvReceitaTitulo
            titulo.text = receita.titulo

            val ingredientes = binding.itemRvReceitaIngredientes
            ingredientes.text = receita.ingredientes

            configuraImageNivel(receita.nivelId)

            configuraImagemTipo(receita.tipoId)

        }

        private fun configuraImageNivel(nivelId: Int?) {
            when (nivelId) {
                1 -> {
                    binding.nivelFacil.load(R.drawable.ic_action_star_true)
                    binding.nivelMedio.load(R.drawable.ic_action_star_false)
                    binding.nivelDificil.load(R.drawable.ic_action_star_false)
                }
                2 -> {
                    binding.nivelFacil.load(R.drawable.ic_action_star_true)
                    binding.nivelMedio.load(R.drawable.ic_action_star_true)
                    binding.nivelDificil.load(R.drawable.ic_action_star_false)
                }
                3 -> {
                    binding.nivelFacil.load(R.drawable.ic_action_star_true)
                    binding.nivelMedio.load(R.drawable.ic_action_star_true)
                    binding.nivelDificil.load(R.drawable.ic_action_star_true)
                }
                else -> {
                    binding.viewGroupNivel.visibility = View.GONE
                }
            }
        }

        private fun configuraImagemTipo(tipoId: Int?) {
            when(tipoId){
                1 -> binding.itemRvReceitaImage.load(R.drawable.refeicao)
                2 -> binding.itemRvReceitaImage.load(R.drawable.lanche)
                3 -> binding.itemRvReceitaImage.load(R.drawable.drink)
                else -> binding.itemRvReceitaImage.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val binding = ItemRvReceitaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ReceitaViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        val receita = receitas[position]
        holder.vincula(receita)
    }

    override fun getItemCount() = receitas.size

    fun atualiza(lista: List<Receita>) {
        receitas = lista
    }

}
