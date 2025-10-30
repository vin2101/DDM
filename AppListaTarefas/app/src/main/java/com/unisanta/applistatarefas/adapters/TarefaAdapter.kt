package com.unisanta.applistatarefas.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.unisanta.applistatarefas.R
import com.unisanta.applistatarefas.models.Tarefa

class TarefaAdapter(
    private val tasks: List<Tarefa>,
    private val onTaskClicked: (Tarefa) -> Unit
) : RecyclerView.Adapter<TarefaAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTaskTitle: TextView = itemView.findViewById(R.id.textViewTaskTitle)
        val textViewTaskDescription: TextView = itemView.findViewById(R.id.textViewTaskDescription)
        val buttonCompleteTask: Button = itemView.findViewById(R.id.buttonCompleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_tarefas, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.textViewTaskTitle.text = task.titulo
        holder.textViewTaskDescription.text = task.descricao

        updateTaskAppearance(holder, task)

        holder.buttonCompleteTask.setOnClickListener {
            onTaskClicked(task)
        }
    }

    private fun updateTaskAppearance(holder: TaskViewHolder, task: Tarefa) {
        if (task.estaCompleta) {
            holder.textViewTaskTitle.paintFlags = holder.textViewTaskTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.textViewTaskDescription.paintFlags = holder.textViewTaskDescription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.buttonCompleteTask.text = "Desmarcar"
        } else {
            holder.textViewTaskTitle.paintFlags = holder.textViewTaskTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.textViewTaskDescription.paintFlags = holder.textViewTaskDescription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.buttonCompleteTask.text = "Concluir"
        }
    }
}

