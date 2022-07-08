package br.com.zup.desafiorickemorty.ui.listadepersonagens.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.desafiorickemorty.R
import br.com.zup.desafiorickemorty.data.model.PersonagemResult
import br.com.zup.desafiorickemorty.databinding.ActivityListaPersonagensBinding
import br.com.zup.desafiorickemorty.ui.listadepersonagens.view.adapter.PersonagemAdapter
import br.com.zup.desafiorickemorty.ui.listadepersonagens.viewmodel.ListPersonagensViewModel
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class ListaPersonagensActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaPersonagensBinding
    private val viewModel: ListPersonagensViewModel by lazy {
        ViewModelProvider(this)[ListPersonagensViewModel::class.java]
    }
    private val adapter: PersonagemAdapter by lazy {
        PersonagemAdapter(mutableListOf())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaPersonagensBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initObserver()
        viewModel.getAllPersonagens()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initObserver(){
        viewModel.listPersonagemState.observe(this){
            when(it){
                is ViewState.Success -> {
                    adapter.apdateListPersonagens(it.data as MutableList<PersonagemResult>)
                }
                is ViewState.Error -> {
                    Toast.makeText(this, "${it.throwable.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}