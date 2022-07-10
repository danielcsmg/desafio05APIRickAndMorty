package br.com.zup.desafiorickemorty.ui.detailcharacter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.desafiorickemorty.R
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.databinding.ActivityDetailCharacterBinding
import br.com.zup.desafiorickemorty.ui.BUNDLE
import br.com.zup.desafiorickemorty.ui.CHARACTER_REM
import br.com.zup.desafiorickemorty.ui.detailcharacter.viewmodel.DetailCharacterViewModel
import br.com.zup.desafiorickemorty.ui.listadepersonagens.viewmodel.ListCharactersViewModel
import com.squareup.picasso.Picasso

class DetailCharacterActivity : AppCompatActivity() {
    private val viewModel: DetailCharacterViewModel by lazy {
        ViewModelProvider(this)[DetailCharacterViewModel::class.java]
    }
    private lateinit var binding: ActivityDetailCharacterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val character = getCharacterDetail()
        character?.let {
            showCharacterDetail(it)
            favoriteCharacter(character)
            supportActionBar?.title = character.name
        }
        favoriteObserver(character)
        actioBar()
    }

    private fun favoriteCharacter(character: CharacterResult) {
        binding.ivFavorite.setOnClickListener{
            character.isFavorite = !character.isFavorite
            updateCharacter(character)
        }
    }

    private fun getCharacterDetail(): CharacterResult? {
        return intent?.getBundleExtra(BUNDLE)?.getParcelable<CharacterResult>(CHARACTER_REM)
    }

    private fun showCharacterDetail(characterResult: CharacterResult) {
        characterResult.apply {
            Picasso.get().load(image).into(binding.ivImageCharacter)
            binding.tvCharacterName.text = getString(R.string.nome_do_personagem, name)
            binding.tvCharacterStatus.text = getString(R.string.status_do_personagem, status)
            binding.tvCharacterEspecie.text = getString(R.string.especie_do_personagem, species)
            binding.tvCharacterGender.text = getString(R.string.genero_do_personagem, gender)
            setFavoriteState(characterResult)
        }
    }

    private fun updateCharacter(characterResult: CharacterResult){
        viewModel.updateCharacterFavorited(characterResult)
    }

    private fun setFavoriteState(characterResult: CharacterResult){
        binding.ivFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                if(characterResult.isFavorite)
                    R.drawable.ic_favorited
                else
                    R.drawable.ic_disfavor
            )
        )
    }

    private fun favoriteObserver(character: CharacterResult?){
        viewModel.characterFavoritedState.observe(this){
            character?.let {
                setFavoriteState(it)
            }
        }
    }

    private fun actioBar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}