package br.com.zup.desafiorickemorty.data.model


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val characterList: List<CharacterResult>
)