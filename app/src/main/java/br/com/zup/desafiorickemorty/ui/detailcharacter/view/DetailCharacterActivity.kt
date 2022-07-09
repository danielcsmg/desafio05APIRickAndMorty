package br.com.zup.desafiorickemorty.ui.detailcharacter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.desafiorickemorty.R
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.databinding.ActivityDetailCharacterBinding
import br.com.zup.desafiorickemorty.ui.BUNDLE
import br.com.zup.desafiorickemorty.ui.CHARACTER_REM
import com.squareup.picasso.Picasso

class DetailCharacterActivity : AppCompatActivity() {
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
        }
    }
}