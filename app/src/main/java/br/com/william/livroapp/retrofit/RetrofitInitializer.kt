package br.com.william.livroapp.retrofit

import br.com.william.livroapp.retrofit.service.LivroService
import br.com.william.livroapp.retrofit.service.UsuarioService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofitLivro = Retrofit.Builder()
            .baseUrl("https://livroapi-william-30scj.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val retrofitUsuario = Retrofit.Builder()
            .baseUrl("https://usuario-api-william-30scj.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun livroService() : LivroService = retrofitLivro.create(LivroService::class.java)
    fun usuarioService() : UsuarioService = retrofitUsuario.create(UsuarioService::class.java)
}