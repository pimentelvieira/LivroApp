package br.com.william.livroapp.livro

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_lista_livros.*

import br.com.william.livroapp.R
import br.com.william.livroapp.adapter.LivroListAdapter
import br.com.william.livroapp.model.Livro
import br.com.william.livroapp.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaLivrosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lista_livros, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val call = RetrofitInitializer().livroService().list()
        call.enqueue(object: Callback<List<Livro>?> {
            override fun onResponse(call: Call<List<Livro>?>?,
                                    response: Response<List<Livro>?>?) {
                response?.body()?.let {
                    val livros : List<Livro> = it
                    setupLista(livros)
                }
            }

            override fun onFailure(call: Call<List<Livro>?>?,
                                   t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    fun setupLista(livros: List<Livro>?) {
        livros.let {
            list_recyclerview.adapter = LivroListAdapter(livros!!, context!!) {livro ->
                abreTelaEdicao(AlteraLivroFragment(), livro)
            }
            val layoutManager = LinearLayoutManager(context)
            list_recyclerview.layoutManager = layoutManager
        }

    }

    fun abreTelaEdicao(fragment: Fragment, livro: Livro) {
        val bundle:Bundle = Bundle()
        bundle.putString("idLivro", livro.id)
        fragment.arguments = bundle

        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.containerFragment, fragment)
        transaction?.commit()
    }
}
