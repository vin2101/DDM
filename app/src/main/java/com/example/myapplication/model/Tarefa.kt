package com.example.myapplication.model

data class Tarefa(
    val nome: String = "",
    val categoria: String = "",
    val status: String = "Pendente" // ou enum: "Pendente", "Concluído", "Cancelado"
) {
    // Firestore converte automaticamente para Map<String, Any>
    fun toMap(): Map<String, Any> {
        return mapOf(
            "nome" to nome,
            "categoria" to categoria,
            "status" to status
        )
    }

    companion object {
        fun fromMap(map: Map<String, Any>): Tarefa {
            return Tarefa(
                nome = map["nome"] as String? ?: "",
                categoria = map["categoria"] as String? ?: "",
                status = map["status"] as String? ?: "Pendente"
            )
        }
    }
}