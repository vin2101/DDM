package br.unisanta.appalunos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {

    private lateinit var txtResultado: TextView
    private lateinit var btnGerarMatricula: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        txtResultado = findViewById(R.id.txt_resultado)
        btnGerarMatricula = findViewById(R.id.btn_gerar_matricula)

        // Corrigido: Não há necessidade de passar o tipo ArrayList<String>
        val alunos = intent.getStringArrayListExtra("ALUNOS") ?: arrayListOf()

        // Exibe a lista de alunos cadastrados
        val alunosListados = alunos.joinToString("\n") { "Aluno: $it" }
        txtResultado.text = "Alunos Cadastrados:\n\n$alunosListados"

        btnGerarMatricula.setOnClickListener {
            if (alunos.isNotEmpty()) {
                // Gera matrícula aleatória para cada aluno
                val matriculas = alunos.map {
                    val numeroMatricula = Random.nextInt(10000, 99999)
                    "$it - Matrícula: $numeroMatricula"
                }
                txtResultado.text = matriculas.joinToString("\n\n")
            } else {
                // Corrigido: Importação do Toast
                Toast.makeText(this, "Nenhum aluno cadastrado para gerar matrícula", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
