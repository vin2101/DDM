package br.unisanta.applivros

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Lista para armazenar os livros (em memória)
    private val listaLivros = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtTitulo = findViewById<EditText>(R.id.edt_titulo)
        val edtAutor = findViewById<EditText>(R.id.edt_autor)
        val btnCadastrar = findViewById<Button>(R.id.btn_cadastrar)
        val btnMostrar = findViewById<Button>(R.id.btn_mostrar)

        btnCadastrar.setOnClickListener {
            val titulo = edtTitulo.text.toString().trim()
            val autor = edtAutor.text.toString().trim()

            if (titulo.isEmpty() || autor.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val livro = "Título: $titulo | Autor: $autor"
            listaLivros.add(livro)

            Toast.makeText(this, "Livro cadastrado!", Toast.LENGTH_SHORT).show()

            // Limpa os campos
            edtTitulo.text.clear()
            edtAutor.text.clear()
        }

        btnMostrar.setOnClickListener {
            if (listaLivros.isEmpty()) {
                Toast.makeText(this, "Nenhum livro cadastrado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putStringArrayListExtra("LIVROS", listaLivros)
            startActivity(intent)
        }
    }
}
