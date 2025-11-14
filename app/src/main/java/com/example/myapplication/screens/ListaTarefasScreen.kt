package com.example.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.Tarefa
import com.example.myapplication.viewmodel.TarefaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefasScreen(
    onNavigateToCadastro: () -> Unit,
    viewModel: TarefaViewModel = viewModel()
) {
    val tarefas by viewModel.tarefas.collectAsState()
    val mensagem by viewModel.mensagem.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarTarefas()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Minhas Tarefas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCadastro) {
                Icon(Icons.Default.Add, contentDescription = "Nova Tarefa")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (mensagem.isNotEmpty() && mensagem.contains("Erro")) {
                Text(
                    text = mensagem,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            if (tarefas.isEmpty()) {
                Text(
                    text = "Nenhuma tarefa cadastrada.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tarefas) { tarefa ->
                        TarefaCard(tarefa)
                    }
                }
            }
        }
    }
}

@Composable
fun TarefaCard(tarefa: Tarefa) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tarefa.nome, style = MaterialTheme.typography.titleMedium)
            Text(text = "Categoria: ${tarefa.categoria}", style = MaterialTheme.typography.bodySmall)
            Row {
                Text("Status: ", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = tarefa.status,
                    color = when (tarefa.status) {
                        "Concluído" -> MaterialTheme.colorScheme.primary
                        "Cancelado" -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.secondary
                    },
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}