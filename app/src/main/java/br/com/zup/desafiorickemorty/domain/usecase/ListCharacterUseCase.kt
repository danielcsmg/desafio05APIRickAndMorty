package br.com.zup.desafiorickemorty.domain.usecase

import android.app.Application
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.domain.repository.CharacterRepository
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_LOAD_CHARACTER_NETWORK
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class ListCharacterUseCase(application: Application) {
    private val charactersRepository = CharacterRepository()

    suspend fun getAllCharacterNetwork(): ViewState<List<CharacterResult>>{
        return try {
            val response = charactersRepository.getAllCharacterNetwork()
            ViewState.Success(response.characterList)
        }catch (e: Exception){
            ViewState.Error(Exception(MESSAGE_ERROR_LOAD_CHARACTER_NETWORK))
        }
    }
}