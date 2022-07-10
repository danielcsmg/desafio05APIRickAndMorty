package br.com.zup.desafiorickemorty.domain.usecase

import android.app.Application
import br.com.zup.desafiorickemorty.data.datasource.local.CharacterDatabase
import br.com.zup.desafiorickemorty.data.datasource.local.dao.CharacterDAO
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.domain.repository.CharacterRepository
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_LOAD_CHARACTER_NETWORK
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class ListCharacterUseCase(application: Application) {
    private val characterDao = CharacterDatabase.getDatabase(application).characterDao()
    private val charactersRepository = CharacterRepository(characterDao)

    private suspend fun getAllCharactersDB(): ViewState<List<CharacterResult>>{
        return try {
            val characters = charactersRepository.getAllCharacters()
            ViewState.Success(characters)
        }catch (e: Exception){
            ViewState.Error(Exception("Não foi possível carregar os personagens."))
        }
    }

    suspend fun getAllCharacterNetwork(): ViewState<List<CharacterResult>>{
        return try {
            val response = charactersRepository.getAllCharacterNetwork()
            charactersRepository.insertAllMoviesDB(response.characterList)
//            getAllCharactersDB()

            ViewState.Success(response.characterList)
        }catch (e: Exception){
            getAllCharactersDB()
        }
    }
}