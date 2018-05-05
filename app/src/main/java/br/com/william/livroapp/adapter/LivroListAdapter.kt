package br.com.william.livroapp.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import br.com.william.livroapp.model.Livro
import android.content.Context
import android.view.LayoutInflater
import br.com.william.livroapp.R
import kotlinx.android.synthetic.main.livro_item.view.*

class LivroListAdapter(private val livros: List<Livro>,
                       private val context: Context,
                       private val onItemClickListener: (livro: Livro) -> Unit) : Adapter<LivroListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val livro = livros[position]

        holder?.let {
            it.bindView(livro)
            it.itemView.setOnClickListener {
                onItemClickListener(livro)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.livro_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(livro: Livro) {
            itemView.livro_item_id.text = livro.id
            itemView.livro_item_nome.text = livro.nome
            itemView.livro_item_autor.text = livro.autor
            itemView.livro_item_editora.text = livro.editora
            itemView.livro_item_paginas.text = livro.paginas.toString()
        }
    }
}