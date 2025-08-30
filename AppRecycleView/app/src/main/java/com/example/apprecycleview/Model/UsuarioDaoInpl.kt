package com.example.apprecycleview.Model

class UsuarioDaoInpl:UsuarioDao {
    companion object{
        val usuarios = mutableListOf<Usuario>()
    }
    override fun adicionarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    override fun obterUsuarios(): List<Usuario> {
       return usuarios
    }

}