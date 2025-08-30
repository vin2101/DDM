package com.example.apprecycleview.Model

interface UsuarioDao {
    fun adicionarUsuario(usuario: Usuario)
    fun obterUsuarios():List<Usuario>
}