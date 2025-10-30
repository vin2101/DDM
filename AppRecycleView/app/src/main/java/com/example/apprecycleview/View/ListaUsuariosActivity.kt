package com.example.apprecycleview.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecycleview.Model.UsuarioDaoInpl
import com.example.apprecycleview.R
import com.example.apprecycleview.adapter.UsuarioAdpter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaUsuariosActivity : AppCompatActivity(R.layout.activity_lista_usuarios) {
    private val dao = UsuarioDaoInpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rvUsuarios = findViewById<RecyclerView>(R.id.rv_usuarios)
        val listaUsuario = dao.obterUsuarios()
        rvUsuarios.layoutManager = LinearLayoutManager(this)
        rvUsuarios.adapter = UsuarioAdpter(listaUsuario)
        val fabVoltar = findViewById<FloatingActionButton>(R.id.fab_voltar)

        fabVoltar.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)}
    }

}