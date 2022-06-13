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
    receitas: List<Receita> = emptyList(),
    var listener: (id: Long) -> Unit = {}
) : RecyclerView.Adapter<ListaReceitasAdapter.ReceitaViewHolder>() {

    private val receitas = receitas.toMutableList()

    inner class ReceitaViewHolder(
        private val binding: ItemRvReceitaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun vincula(receita: Receita) {
            val titulo = binding.itemRvReceitaTitulo
            titulo.text = receita.titulo

            val ingredientes = binding.itemRvReceitaIngredientes
            ingredientes.text = receita.ingredientes

            configuraImageNivel(receita.nivelId)
            configuraImagemTipo(receita.tipoId)

            visibility(view = binding.itemRvReceitaImage, receita.tipoId)
            visibility(view = binding.viewGroupNivel, receita.nivelId)
        }

        // Valida a visibility das views Tipo e Nivel
        fun visibility(view: View, param: Int?) {
            return if (param != null) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }

        // Configura image de dificuldade
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
            }
        }

        // Configura image de Tipo
        private fun configuraImagemTipo(tipoId: Int?) {
            when (tipoId) {
                1 -> binding.itemRvReceitaImage.load(R.drawable.refeicao)
                2 -> binding.itemRvReceitaImage.load(R.drawable.lanche)
                3 -> binding.itemRvReceitaImage.load(R.drawable.drink)
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

        holder.itemView.setOnClickListener {
            listener(receita.id)
        }

    }

    override fun getItemCount() = receitas.size

    fun atualiza(lista: List<Receita>) {
        this.receitas.clear()
        notifyItemRangeRemoved(0, this.receitas.size)
        this.receitas.addAll(lista)
        notifyDataSetChanged()
    }
}
