package br.com.william.livroapp.livro

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Toast

import br.com.william.livroapp.R
import br.com.william.livroapp.model.Livro
import br.com.william.livroapp.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.fragment_novo_livro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NovoLivroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_novo_livro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configuraBotaoSalvar()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_salva_livro, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun configuraBotaoSalvar() {
        btSalvar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var nome = inputNome.editText?.text
                var autor = inputAutor.editText?.text
                var editora = inputEditora.editText?.text
                var paginas = inputPaginas.editText?.text

                Log.e("NovoLivro", "" + nome + " " + autor + " " + editora + " " + paginas)

                val livro : Livro = Livro(null, nome.toString(), autor.toString(), editora.toString(), paginas.toString().toInt())

                val call = RetrofitInitializer().livroService().insert(livro)
                call.enqueue(object: Callback<Livro?> {
                    override fun onResponse(call: Call<Livro?>?, response: Response<Livro?>?) {
                        if(response?.isSuccessful == true) {
                            Toast.makeText(context,
                                    "Livro Inserido Com Sucesso!",
                                    Toast.LENGTH_SHORT).show()
                            limparCampos()
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

    private fun limparCampos() {
        inputNome.editText?.setText("")
        inputAutor.editText?.setText("")
        inputEditora.editText?.setText("")
        inputPaginas.editText?.setText("")
        inputNome.requestFocus()
    }
}
