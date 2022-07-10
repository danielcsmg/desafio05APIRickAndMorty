package br.com.zup.desafiorickemorty.domain.repository

import android.content.Context
import br.com.zup.desafiorickemorty.data.datasource.local.CharacterDatabase
import br.com.zup.desafiorickemorty.data.datasource.local.dao.CharacterDAO
import br.com.zup.desafiorickemorty.data.datasource.remote.RetroftService
import br.com.zup.desafiorickemorty.data.model.CharacterResponse
import br.com.zup.desafiorickemorty.data.model.CharacterResult

class CharacterRepository(context: Context) {
    private val characterDAO = CharacterDatabase.getDatabase(context).characterDao()
    suspend fun getAllCharacterNetwork(): CharacterResponse {
        return RetroftService.apiService.getInfoAPIRickAndMortyNetwork()
    }

    suspend fun insertAllCharactersDB(listCharacters: List<CharacterResult>){
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