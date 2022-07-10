package br.com.zup.desafiorickemorty.data.datasource.remote

import br.com.zup.desafiorickemorty.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI {
    @GET("character")
    suspend fun getInfoAPIRickAndMortyNetwork(
        @Query("page")
        page: Int = 1
    ): CharacterResponse
}