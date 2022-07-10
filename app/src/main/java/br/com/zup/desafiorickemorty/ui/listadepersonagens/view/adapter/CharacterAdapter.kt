package br.com.zup.desafiorickemorty.ui.listadepersonagens.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private var listCharacters: MutableList<CharacterResult> = mutableListOf(),
    val clickDetail: (character: CharacterResult) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = listCharacters[position]
        holder.showCharacterInfo(character)
        holder.binding.cvCharacter.setOnClickListener{
            clickDetail(character)
        }
    }

    override fun getItemCount() = listCharacters.size

    fun updateListCharacters(newList: MutableList<CharacterResult>) {
        listCharacters = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun showCharacterInfo(character: CharacterResult) {
            Picasso.get().load(character.image).into(binding.ivCharacter)
            binding.tvNameCharacter.text = character.name
        }
    }
}