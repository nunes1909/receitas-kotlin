package com.example.receitas.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.receitas.*
import com.example.receitas.databinding.ItemRvReceitaBinding
import com.example.receitas.presenter.model.ReceitaPresenter


class ListaReceitasAdapter(
    receitas: List<ReceitaPresenter> = emptyList(),
    var listener: (id: Long) -> Unit = {}
) : RecyclerView.Adapter<ListaReceitasAdapter.ReceitaViewHolder>() {

    private val receitas = receitas.toMutableList()

    inner class ReceitaViewHolder(
        private val binding: ItemRvReceitaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun vincula(receita: ReceitaPresenter) {
            val titulo = binding.itemRvReceitaTitulo
            titulo.text = receita.titulo

            if (receita.imagem != null && receita.exibeImagem == 1) {
                binding.itemRvReceitaImage.load(receita.imagem)
            } else {
                configuraImagemTipo(receita.tipo)
            }

            val ingredientes = binding.itemRvReceitaIngredientes
            ingredientes.text = receita.ingredientes

            configuraImageNivel(receita.nivel)
        }

        // Configura image de dificuldade
        private fun configuraImageNivel(nivel: String?) {
            when (nivel) {
                FACIL -> {
                    binding.nivelFacil.load(R.drawable.ic_action_star_true)
                    binding.nivelMedio.load(R.drawable.ic_action_star_false)
                    binding.nivelDificil.load(R.drawable.ic_action_star_false)
                }
                MEDIO -> {
                    binding.nivelFacil.load(R.drawable.ic_action_star_true)
                    binding.nivelMedio.load(R.drawable.ic_action_star_true)
                    binding.nivelDificil.load(R.drawable.ic_action_star_false)
                }
                DIFICIL -> {
                    binding.nivelFacil.load(R.drawable.ic_action_star_true)
                    binding.nivelMedio.load(R.drawable.ic_action_star_true)
                    binding.nivelDificil.load(R.drawable.ic_action_star_true)
                }
            }
        }

        // Configura image de Tipo
        private fun configuraImagemTipo(tipo: String?) {
            when (tipo) {
                REFEICAO -> binding.itemRvReceitaImage.load(R.drawable.refeicao)
                SAUDAVEL -> binding.itemRvReceitaImage.load(R.drawable.saudavel)
                LANCHE -> binding.itemRvReceitaImage.load(R.drawable.lanche)
                DOCE -> binding.itemRvReceitaImage.load(R.drawable.doce)
                DRINK -> binding.itemRvReceitaImage.load(R.drawable.drink)
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

        // Busca o Id do item clicado
        holder.itemView.setOnClickListener {
            listener(receita.id)
        }
    }

    override fun getItemCount() = receitas.size

    // Atualiza a lista do Adapter
    fun atualiza(lista: List<ReceitaPresenter>) {
        this.receitas.clear()
        notifyItemRangeRemoved(0, this.receitas.size)
        this.receitas.addAll(lista)
        notifyDataSetChanged()
    }
}
