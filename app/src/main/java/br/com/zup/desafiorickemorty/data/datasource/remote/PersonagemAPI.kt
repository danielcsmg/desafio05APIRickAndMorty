package br.com.zup.desafiorickemorty.data.datasource.remote

import br.com.zup.desafiorickemorty.data.model.CharacterResponse
import retrofit2.http.GET

interface PersonagemAPI {
    @GET("character")
    suspend fun getInfoAPIRickAndMortyNetwork(): CharacterResponse
}