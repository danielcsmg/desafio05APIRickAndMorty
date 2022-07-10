package br.com.zup.desafiorickemorty.domain.usecase

import android.app.Application
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.domain.repository.CharacterRepository
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class ListCharacterUseCase(application: Application) {
    private val charactersRepository = CharacterRepository(application)

    private suspend fun getAllCharactersDB(): ViewState<List<CharacterResult>>{
        return try {
            val characters = charactersRepository.getAllCharacters()
            ViewState.Success(characters)
        }catch (e: Exception){
            ViewState.Error(Exception("Não foi possível carregar os personagens."))
        }
    }

    suspend fun getAllCharacters(): ViewState<List<CharacterResult>>{
        return try {
            val response = charactersRepository.getAllCharacterNetwork()
            charactersRepository.insertAllCharactersDB(response.characterList)
            getAllCharactersDB()

        }catch (e: Exception){
            getAllCharactersDB()
        }
    }
}