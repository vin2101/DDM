package br.unisanta.appsoma

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtNum1 = findViewById<EditText>(R.id.edt_num1)
        val edtNum2 = findViewById<EditText>(R.id.edt_num2)
        val edtOperacao = findViewById<TextInputEditText>(R.id.edt_Operacao)
        val btnCalc = findViewById<Button>(R.id.btn_calc)
        val txtResultado = findViewById<TextView>(R.id.txv_resultado)

        btnCalc.setOnClickListener {

            val valor1 = edtNum1.text.toString().toIntOrNull()
            val valor2 = edtNum2.text.toString().toIntOrNull()
            val operacao = edtOperacao.text.toString().trim()

            if (valor1 == null || valor2 == null) {
                Toast.makeText(this, "Digite números válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (operacao !in listOf("+", "-", "*", "/")) {
                Toast.makeText(this, "Operação inválida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultado = when (operacao) {
                "+" -> valor1 + valor2
                "-" -> valor1 - valor2
                "*" -> valor1 * valor2
                "/" -> {
                    if (valor2 == 0) {
                        Toast.makeText(this, "Divisão por zero!", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    } else {
                        valor1 / valor2
                    }
                }
                else -> 0
            }

            txtResultado.text = "O resultado é: $resultado"
        }
    }
}
