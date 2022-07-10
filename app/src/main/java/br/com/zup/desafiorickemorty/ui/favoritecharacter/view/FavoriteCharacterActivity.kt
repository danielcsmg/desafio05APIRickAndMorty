package br.com.zup.desafiorickemorty.ui.favoritecharacter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.databinding.ActivityFavoriteCharacterBinding
import br.com.zup.desafiorickemorty.ui.BUNDLE
import br.com.zup.desafiorickemorty.ui.CHARACTER_REM
import br.com.zup.desafiorickemorty.ui.FAVORITES
import br.com.zup.desafiorickemorty.ui.detailcharacter.view.DetailCharacterActivity
import br.com.zup.desafiorickemorty.ui.favoritecharacter.view.adapter.FavoriteCharacterAdapter
import br.com.zup.desafiorickemorty.ui.favoritecharacter.viewmodel.FavoriteCharactersViewModel
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class FavoriteCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteCharacterBinding
    private val viewModel: FavoriteCharactersViewModel by lazy {
        ViewModelProvider(this)[FavoriteCharactersViewModel::class.java]
    }
    private val adapter: FavoriteCharacterAdapter by lazy {
        FavoriteCharacterAdapter(mutableListOf(), this::redirectToDetail)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoriteCharacterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initObserver()
        actioBar()
        initRV()
        viewModel.getFavoriteCharacters()
    }

    private fun initRV(){
        binding.rvFavoriteCharacter.adapter = adapter
        binding.rvFavoriteCharacter.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initObserver(){
        viewModel.favoriteCharactersListState.observe(this){
            when(it){
                is ViewState.Success -> {
                    adapter.updateListCharacters(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_SHORT).show()
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

    private fun actioBar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = FAVORITES
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}