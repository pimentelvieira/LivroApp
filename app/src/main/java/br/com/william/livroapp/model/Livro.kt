package br.com.william.livroapp.model

data class Livro (var id: String?,
                  var nome: String,
                  var autor: String,
                  var editora: String,
                  var paginas: Int) {

    constructor() : this("", "", "", "", 0)
}