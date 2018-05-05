package br.com.william.livroapp.retrofit.service

import br.com.william.livroapp.model.Livro
import retrofit2.Call
import retrofit2.http.*

interface LivroService {

    @GET("/livro")
    fun list() : Call<List<Livro>>

    @POST("/livro")
    fun insert(@Body livro: Livro) : Call<Livro>

    @PUT("/livro/{id}")
    fun alter(@Body livro: Livro, @Path("id") id: String) : Call<Livro>

    @GET("/livro/{id}")
    fun getLivro(@Path("id") id: String?) : Call<Livro>

    @DELETE("/livro/{id}")
    fun delete(@Path("id") id: String?) : Call<Void>
}