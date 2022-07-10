package br.com.zup.desafiorickemorty.data.datasource.remote

import br.com.zup.desafiorickemorty.data.model.CharacterResponse
import retrofit2.http.GET

interface CharacterAPI {
    @GET("character")
    suspend fun getInfoAPIRickAndMortyNetwork(): CharacterResponse
}