package com.example.apprecycleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecycleview.Model.Usuario
import com.example.apprecycleview.R

class UsuarioAdpter(private val usuarios:List<Usuario>):
   RecyclerView.Adapter<UsuarioAdpter.ViewHolder>()
{
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txvNomeUsuario = itemView.findViewById<TextView>(R.id.txv_nome_usuario)
        val txvEmail: TextView = itemView.findViewById(R.id.txv_email)
        val txvTelefone: TextView = itemView.findViewById(R.id.txv_telefone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  usuarios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.txvNomeUsuario.text = usuario.nome
        holder.txvEmail.text = usuario.email
        holder.txvTelefone.text = usuario.telefone
    }
}