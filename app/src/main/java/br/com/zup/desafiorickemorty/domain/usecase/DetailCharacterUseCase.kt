package br.com.zup.desafiorickemorty.domain.usecase

import android.app.Application
import br.com.zup.desafiorickemorty.data.datasource.local.CharacterDatabase
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.domain.repository.CharacterRepository
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class DetailCharacterUseCase(application: Application) {
    private val characterDao = CharacterDatabase.getDatabase(application).characterDao()
    private val charactersRepository = CharacterRepository(characterDao)

    suspend fun updateCharacterFavorite(characterResult: CharacterResult): ViewState<CharacterResult> {
        return try {
            charactersRepository.updateCharacterFavorited(characterResult)
            ViewState.Success(characterResult)
        }catch (e: Exception){
            ViewState.Error(Exception())
        }
    }
}