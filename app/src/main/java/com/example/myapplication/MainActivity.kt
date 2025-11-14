package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.myapplication.screens.CadastroTarefaScreen
import com.example.myapplication.screens.ListaTarefasScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var telaAtual by remember { mutableStateOf("lista") }

                    when (telaAtual) {
                        "lista" -> ListaTarefasScreen(
                            onNavigateToCadastro = { telaAtual = "cadastro" }
                        )
                        "cadastro" -> CadastroTarefaScreen(
                            onNavigateBack = { telaAtual = "lista" }
                        )
                    }
                }
            }
        }
    }
}