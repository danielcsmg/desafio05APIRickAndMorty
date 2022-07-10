package br.com.zup.desafiorickemorty.domain.repository

import br.com.zup.desafiorickemorty.data.datasource.local.dao.CharacterDAO
import br.com.zup.desafiorickemorty.data.datasource.remote.RetroftService
import br.com.zup.desafiorickemorty.data.model.CharacterResponse
import br.com.zup.desafiorickemorty.data.model.CharacterResult

class CharacterRepository(private  val characterDAO: CharacterDAO) {
    suspend fun getAllCharacterNetwork(): CharacterResponse {
        return RetroftService.apiService.getInfoAPIRickAndMortyNetwork()
    }

    suspend fun insertAllMoviesDB(listCharacters: List<CharacterResult>){
        characterDAO.insertAllCharacters(listCharacters)
    }

    suspend fun getAllCharacters(): List<CharacterResult> {
        return characterDAO.getAllCharacters()
    }

    suspend fun updateCharacterFavorited(character: CharacterResult){
        characterDAO.updateCharacterFavorite(character)
    }

    suspend fun getAllFavoriteCharacters(): List<CharacterResult> {
        return characterDAO.getAllFavoriteCharacters()
    }
}