package br.com.zup.desafiorickemorty.domain.usecase

import android.content.Context
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.domain.repository.CharacterRepository
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_LOAD_FAVORITE_CHARACTER
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class FavoriteCharacterListUseCase(context: Context) {
    private val characterRepository = CharacterRepository(context)

    suspend fun getFavoriteCharacters(): ViewState<List<CharacterResult>> {
        return try {
            val characters = characterRepository.getAllFavoriteCharacters()
            ViewState.Success(characters)
        }catch (e: Exception){
            ViewState.Error(Exception(MESSAGE_ERROR_LOAD_FAVORITE_CHARACTER))
        }
    }
}