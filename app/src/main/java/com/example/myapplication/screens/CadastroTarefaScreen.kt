package com.example.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.Tarefa
import com.example.myapplication.viewmodel.TarefaViewModel

@Composable
fun CadastroTarefaScreen(
    onNavigateBack: () -> Unit,
    viewModel: TarefaViewModel = viewModel()
) {
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Pendente") }
    val statusOptions = listOf("Pendente", "Concluído", "Cancelado")

    val mensagem by viewModel.mensagem.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Cadastro de Tarefa", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoria") },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = false, // simplificado — ou use um state boolean
            onExpandedChange = { }
        ) {
            OutlinedTextField(
                value = status,
                onValueChange = { },
                readOnly = true,
                label = { Text("Status") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                modifier = Modifier.menuAnchor()
            )
            // Dropdown simplificado (ou use DropdownMenu para interação)
            DropdownMenu(
                expanded = false,
                onDismissRequest = { }
            ) {
                statusOptions.forEach { opcao ->
                    DropdownMenuItem(
                        text = { Text(opcao) },
                        onClick = { status = opcao }
                    )
                }
            }
        }

        Button(
            onClick = {
                if (nome.isNotBlank() && categoria.isNotBlank()) {
                    val tarefa = Tarefa(nome, categoria, status)
                    viewModel.salvarTarefa(tarefa)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Tarefa")
        }

        if (mensagem.isNotEmpty()) {
            Text(
                text = mensagem,
                color = if (mensagem.contains("sucesso")) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botão "Voltar"
        TextButton(onClick = onNavigateBack) {
            Text("← Voltar para Lista")
        }
    }
}