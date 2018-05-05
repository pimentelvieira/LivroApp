package br.com.william.livroapp.retrofit.service

import br.com.william.livroapp.model.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UsuarioService {
    @GET("/usuario")
    fun getLivro(@Query("login") login: String?, @Query("senha") senha: String?) : Call<Usuario>
}