package br.com.zup.desafiorickemorty.domain.repository

import br.com.zup.desafiorickemorty.data.datasource.remote.RetroftService
import br.com.zup.desafiorickemorty.data.model.CharacterResponse

class CharacterRepository {
    suspend fun getAllCharacterNetwork(): CharacterResponse {
        return RetroftService.apiService.getInfoAPIRickAndMortyNetwork()
    }
}