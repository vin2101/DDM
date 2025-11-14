package com.example.myapplication.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.example.myapplication.model.Tarefa
import kotlinx.coroutines.tasks.await

class TarefaRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("tarefas")

    suspend fun salvarTarefa(tarefa: Tarefa): String {
        val docRef = collection.document() // gera ID autogerado
        docRef.set(tarefa.toMap()).await()
        return docRef.id
    }

    suspend fun obterTodasTarefas(): List<Tarefa> {
        val snapshot = collection.get().await()
        return snapshot.toObjects(Tarefa::class.java)
    }
}