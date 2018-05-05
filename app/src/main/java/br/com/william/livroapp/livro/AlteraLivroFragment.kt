package br.com.william.livroapp.livro


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import br.com.william.livroapp.R
import br.com.william.livroapp.model.Livro
import br.com.william.livroapp.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.fragment_altera_livro.*

class AlteraLivroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_altera_livro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configuraBotaoAlterar()
        configuraBotaoExcluir()
        super.onViewCreated(view, savedInstanceState)
        val bundle : Bundle? = arguments
        val idLivro : String? = bundle?.getString("idLivro")

        val call = RetrofitInitializer().livroService().getLivro(idLivro)
        call.enqueue(object: Callback<Livro?> {
            override fun onResponse(call: Call<Livro?>?, response: Response<Livro?>?) {
                if(response?.isSuccessful == true) {

                    response?.body()?.let {
                        val livro : Livro = it

                        inputId.editText?.setText(livro.id)
                        inputNome.editText?.setText(livro.nome)
                        inputAutor.editText?.setText(livro.autor)
                        inputEditora.editText?.setText(livro.editora)
                        inputPaginas.editText?.setText(livro.paginas.toString())
                    }

                } else {
                    Toast.makeText(context,
                            "Erro",
                            Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Livro?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    fun configuraBotaoAlterar() {
        btAlterar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var id = inputId.editText?.text
                var nome = inputNome.editText?.text
                var autor = inputAutor.editText?.text
                var editora = inputEditora.editText?.text
                var paginas = inputPaginas.editText?.text

                Log.e("AlteraLivro", "" + id + " " + nome + " " + autor + " " + editora + " " + paginas)

                val livro : Livro = Livro(id.toString(), nome.toString(), autor.toString(), editora.toString(), paginas.toString().toInt())

                val call = RetrofitInitializer().livroService().alter(livro, id.toString())
                call.enqueue(object: Callback<Livro?> {
                    override fun onResponse(call: Call<Livro?>?, response: Response<Livro?>?) {
                        if(response?.isSuccessful == true) {
                            Toast.makeText(context,
                                    "Livro Alterado Com Sucesso!",
                                    Toast.LENGTH_SHORT).show()
                            changeFragment(ListaLivrosFragment())
                        } else {
                            Toast.makeText(context,
                                    "Erro",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Livro?>?, t: Throwable?) {
                        Log.e("onFailure error", t?.message)
                    }
                })
            }
        })
    }

    fun configuraBotaoExcluir() {
        btExcluir.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var id = inputId.editText?.text

                Log.e("ExcluiLivro", "" + id)

                val call = RetrofitInitializer().livroService().delete(id.toString())
                call.enqueue(object: Callback<Void?> {
                    override fun onResponse(call: Call<Void?>?, response: Response<Void?>?) {
                        if(response?.isSuccessful == true) {
                            Toast.makeText(context,
                                    "Livro Excluido Com Sucesso!",
                                    Toast.LENGTH_SHORT).show()
                            changeFragment(ListaLivrosFragment())
                        } else {
                            Toast.makeText(context,
                                    "Erro",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void?>?, t: Throwable?) {
                        Log.e("onFailure error", t?.message)
                    }
                })
            }
        })
    }

    fun changeFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.containerFragment, fragment)
        transaction?.commit()
    }
}
