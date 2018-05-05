package br.com.william.livroapp.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.william.livroapp.MainActivity
import br.com.william.livroapp.R
import br.com.william.livroapp.model.Usuario
import br.com.william.livroapp.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        configuraBotaoLogar()
    }

    fun configuraBotaoLogar() {
        btLogar.setOnClickListener() {
            var login = inputLogin.editText?.text
            var senha = inputSenha.editText?.text

            val call = RetrofitInitializer().usuarioService().getLivro(login.toString(), senha.toString())
            Log.e("Login", "" + login.toString() + " " + senha.toString() + " " + call.toString())

            call.enqueue(object: Callback<Usuario?> {
                override fun onResponse(call: Call<Usuario?>?, response: Response<Usuario?>?) {
                    Log.e("Login", "" + response?.body()?.id.toString())
                    if(response?.isSuccessful == true) {
                        Toast.makeText(baseContext,
                                "Usuário Logado Com Sucesso!",
                                Toast.LENGTH_SHORT).show()
                        startActivity(Intent(baseContext, MainActivity::class.java))
                    } else {
                        Toast.makeText(baseContext,
                                "Usuário Não Encontrado",
                                Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Usuario?>?, t: Throwable?) {
                    Toast.makeText(baseContext,
                            "Usuário Não Encontrado",
                            Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
