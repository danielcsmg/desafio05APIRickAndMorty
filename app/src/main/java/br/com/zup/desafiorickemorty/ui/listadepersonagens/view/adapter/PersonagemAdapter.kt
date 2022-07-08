package br.com.zup.desafiorickemorty.ui.listadepersonagens.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.desafiorickemorty.data.model.PersonagemResult
import br.com.zup.desafiorickemorty.databinding.ItemPersonagemBinding
import com.squareup.picasso.Picasso

class PersonagemAdapter(
    var listPersonagens: MutableList<PersonagemResult> = mutableListOf()
) : RecyclerView.Adapter<PersonagemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPersonagemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personagem = listPersonagens[position]
        holder.showPersonagemInfo(personagem)
    }

    override fun getItemCount() = listPersonagens.size

    fun apdateListPersonagens(newList: MutableList<PersonagemResult>){
        listPersonagens = newList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPersonagemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showPersonagemInfo(personagemResult: PersonagemResult) {
            Picasso.get().load(personagemResult.image).into(binding.ivPersonagem)
            binding.tvNomePersonagem.text = personagemResult.name
        }
    }

}