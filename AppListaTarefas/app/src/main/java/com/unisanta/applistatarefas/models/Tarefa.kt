package com.unisanta.applistatarefas.models

data class Tarefa (
    val titulo: String,
    val descricao: String,
    var estaCompleta: Boolean = false
)