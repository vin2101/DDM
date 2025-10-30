package br.unisanta.applivros

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val txtDetalhes = findViewById<TextView>(R.id.txt_detalhes)

        val livros = intent.getStringArrayListExtra("LIVROS")

        val listaFormatada = livros?.joinToString("\n\n") ?: "Nenhum livro recebido"

        txtDetalhes.text = "Livros Cadastrados:\n\n$listaFormatada"
    }
}
