package com.example.apprecycleview.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apprecycleview.Model.Usuario
import com.example.apprecycleview.Model.UsuarioDaoInpl
import com.example.apprecycleview.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val dao = UsuarioDaoInpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val edtNomeUsuario = findViewById<EditText>(R.id.edt_nome_usuario)
        val edtEmailUsuario = findViewById<EditText>(R.id.edt_email_usuario)
        val edtTelefoneUsuario = findViewById<EditText>(R.id.edt_telefone_usuario)
        val btnCadastrar= findViewById<Button>(R.id.btn_cadastro)
        val fabAvanca = findViewById<FloatingActionButton>(R.id.fab_avanco)

        btnCadastrar.setOnClickListener{
            val nome = edtNomeUsuario.text.toString()
            val email = edtEmailUsuario.text.toString()
            val telefone = edtTelefoneUsuario.text.toString()
            val usuario = Usuario(nome, telefone, email)
            dao.adicionarUsuario(usuario)
            edtNomeUsuario.text.clear()
            edtEmailUsuario.text.clear()
            edtTelefoneUsuario.text.clear()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Sucesso")
            builder.setMessage("Cadastro Ok!")
            val alert = builder.create()
            alert.show()
        }
        fabAvanca.setOnClickListener{
            val intent = Intent(this, ListaUsuariosActivity::class.java)
            startActivity(intent)
        }
    }
}