package br.unisanta.appalunos

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val listaAlunos = ArrayList<String>() // Lista para armazenar os alunos cadastrados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNomeAluno = findViewById<EditText>(R.id.edt_nome_aluno)
        val btnCadastrar = findViewById<Button>(R.id.btn_continuar)
        val btnMostrar = findViewById<Button>(R.id.btn_mostrar)

        // Cadastrar um aluno
        btnCadastrar.setOnClickListener {
            val nomeAluno = edtNomeAluno.text.toString().trim()

            if (nomeAluno.isEmpty()) {
                Toast.makeText(this, "Digite o nome do aluno", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            listaAlunos.add(nomeAluno) // Adiciona o aluno na lista

            Toast.makeText(this, "Aluno $nomeAluno cadastrado!", Toast.LENGTH_SHORT).show()

            // Limpa o campo
            edtNomeAluno.text.clear()
        }

        // Mostrar alunos cadastrados
        btnMostrar.setOnClickListener {
            if (listaAlunos.isEmpty()) {
                Toast.makeText(this, "Nenhum aluno cadastrado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putStringArrayListExtra("ALUNOS", listaAlunos) // Passa a lista de alunos para a segunda tela
            startActivity(intent)
        }
    }
}
