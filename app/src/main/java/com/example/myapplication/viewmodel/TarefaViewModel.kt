package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.TarefaRepository
import com.example.myapplication.model.Tarefa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TarefaViewModel : ViewModel() {
    private val repository = TarefaRepository()

    private val _tarefas = MutableStateFlow<List<Tarefa>>(emptyList())
    val tarefas: StateFlow<List<Tarefa>> = _tarefas

    private val _mensagem = MutableStateFlow("")
    val mensagem: StateFlow<String> = _mensagem

    fun carregarTarefas() {
        viewModelScope.launch {
            try {
                _tarefas.value = repository.obterTodasTarefas()
            } catch (e: Exception) {
                _mensagem.value = "Erro ao carregar tarefas: ${e.message}"
            }
        }
    }

    fun salvarTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                repository.salvarTarefa(tarefa)
                _mensagem.value = "Tarefa salva com sucesso!"
                carregarTarefas() // atualiza lista
            } catch (e: Exception) {
                _mensagem.value = "Erro ao salvar: ${e.message}"
            }
        }
    }
}