package com.example.receitas.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.receitas.R
import com.example.receitas.databinding.ItemRvReceitaBinding
import com.example.receitas.domain.model.Receita

class ListaReceitasAdapter(
    var listener: (id: Long) -> Unit = {}
) : RecyclerView.Adapter<ListaReceitasAdapter.ReceitaViewHolder>() {

    private var dataReceitas: List<Receita> = mutableListOf()

    inner class ReceitaViewHolder(
        binding: ItemRvReceitaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val binding = binding

        fun vincula(dataReceita: Receita) {
            val titulo = binding.itemRvReceitaTitulo
            titulo.text = dataReceita.titulo

            val ingredientes = binding.itemRvReceitaIngredientes
            ingredientes.text = dataReceita.ingredientes

            configuraImageNivel(dataReceita.nivelId)

            configuraImagemTipo(dataReceita.tipoId)

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
            when (tipoId) {
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
        val receita = dataReceitas[position]
        holder.vincula(receita)

        holder.itemView.setOnClickListener {
            listener(getItemId(position))
        }

    }

    override fun getItemCount() = dataReceitas.size

    fun atualiza(lista: List<Receita>) {
        dataReceitas = lista
        notifyDataSetChanged()
    }

}
