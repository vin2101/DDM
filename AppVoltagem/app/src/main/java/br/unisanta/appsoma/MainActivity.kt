package br.unisanta.appsoma

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // IDs do layout
        val edtResistencia = findViewById<EditText>(R.id.edt_num1)
        val edtCorrente = findViewById<EditText>(R.id.edt_num2)
        val btnCalcular = findViewById<Button>(R.id.btn_calc)
        val txtResultado = findViewById<TextView>(R.id.txv_resultado)

        btnCalcular.setOnClickListener {
            val resistencia = edtResistencia.text.toString().toDoubleOrNull()
            val corrente = edtCorrente.text.toString().toDoubleOrNull()

            if (resistencia == null || corrente == null) {
                Toast.makeText(this, "Digite valores válidos para R e I", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tensao = resistencia * corrente
            txtResultado.text = "A tensão (V) é: $tensao V"
        }
    }
}
