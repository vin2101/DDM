package com.unisanta.applistatarefas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.unisanta.applistatarefas.adapters.TarefaAdapter
import com.unisanta.applistatarefas.models.Tarefa

// Passo 4: A Lógica da MainActivity (versão com findViewById)
class MainActivity : AppCompatActivity() {

    // Removemos a referência ao "binding"
    // private lateinit var binding: ActivityMainBinding

    private lateinit var taskAdapter: TarefaAdapter
    private val taskList = mutableListOf<Tarefa>()

    private lateinit var recyclerViewTasks: RecyclerView
    private lateinit var fabAddTask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks)
        fabAddTask = findViewById(R.id.fabAddTask)

        setupRecyclerView()

        fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun setupRecyclerView() {
        taskAdapter = TarefaAdapter(taskList) { task ->
            toggleTaskStatus(task)
        }

        recyclerViewTasks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }
    }

    private fun showAddTaskDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.modal_add_tarefa, null)

        val editTextTaskTitle = dialogView.findViewById<EditText>(R.id.editTextTaskTitle)
        val editTextTaskDescription = dialogView.findViewById<EditText>(R.id.editTextTaskDescription)

        AlertDialog.Builder(this)
            .setTitle("Nova Tarefa")
            .setView(dialogView)
            .setPositiveButton("Adicionar") { dialog, _ ->
                val title = editTextTaskTitle.text.toString()
                val description = editTextTaskDescription.text.toString()

                if (title.isNotBlank()) {
                    val newTask = Tarefa(titulo = title, descricao = description)
                    addTask(newTask)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }

    private fun addTask(task: Tarefa) {
        taskList.add(task)
        taskAdapter.notifyItemInserted(taskList.size - 1)
    }

    private fun toggleTaskStatus(task: Tarefa) {
        val index = taskList.indexOf(task)
        if (index != -1) {
            taskList[index].estaCompleta = !taskList[index].estaCompleta
            taskAdapter.notifyItemChanged(index)
        }
    }
}

