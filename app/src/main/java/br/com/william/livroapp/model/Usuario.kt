package br.com.william.livroapp.model

data class Usuario (var id: String?,
                    var login: String,
                    var senha: String) {

    constructor() : this("", "", "")
}