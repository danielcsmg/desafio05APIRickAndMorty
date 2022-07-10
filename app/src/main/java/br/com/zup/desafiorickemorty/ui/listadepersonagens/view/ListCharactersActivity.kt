package br.com.zup.desafiorickemorty.ui.listadepersonagens.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.databinding.ActivityListCharactersBinding
import br.com.zup.desafiorickemorty.ui.BUNDLE
import br.com.zup.desafiorickemorty.ui.CHARACTER_REM
import br.com.zup.desafiorickemorty.ui.detailcharacter.view.DetailCharacterActivity
import br.com.zup.desafiorickemorty.ui.favoritecharacter.view.FavoriteCharacterActivity
import br.com.zup.desafiorickemorty.ui.listadepersonagens.view.adapter.CharacterAdapter
import br.com.zup.desafiorickemorty.ui.listadepersonagens.viewmodel.ListCharactersViewModel
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class ListCharactersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListCharactersBinding
    private val viewModel: ListCharactersViewModel by lazy {
        ViewModelProvider(this)[ListCharactersViewModel::class.java]
    }
    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter(mutableListOf(), this::redirectToDetail)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initObserver()
        viewModel.getAllCharacters()
        initRecyclerView()
        redirectToFavoriteList()
    }

    private fun initRecyclerView(){
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initObserver(){
        viewModel.listCharactersState.observe(this){
            when(it){
                is ViewState.Success -> {
                    adapter.updateListCharacters(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(this, "${it.throwable.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun redirectToDetail(character: CharacterResult){
        val bundle = bundleOf(CHARACTER_REM to character)
        val intent = Intent(this, DetailCharacterActivity::class.java).apply {
            putExtra(BUNDLE, bundle)
        }
        startActivity(intent)
    }

    private fun redirectToFavoriteList(){
        binding.fabFavoriteList.setOnClickListener {
            startActivity(Intent(this, FavoriteCharacterActivity::class.java))
        }
    }
}