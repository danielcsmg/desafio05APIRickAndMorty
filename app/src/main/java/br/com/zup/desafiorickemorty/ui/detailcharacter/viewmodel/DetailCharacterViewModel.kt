package br.com.zup.desafiorickemorty.ui.detailcharacter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.domain.usecase.DetailCharacterUseCase
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_FAVORITE_CHARACTER_NETWORK
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailCharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val detailCharacterUseCase = DetailCharacterUseCase(application)
    private val _characterFavitedState = MutableLiveData<ViewState<CharacterResult>>()
    val characterFavoritedState: LiveData<ViewState<CharacterResult>> = _characterFavitedState

    fun updateCharacterFavorited(characterResult: CharacterResult){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    detailCharacterUseCase.updateCharacterFavorite(characterResult)
                }
                _characterFavitedState.value = response
            }catch (e: Exception){
                ViewState.Error(Exception(MESSAGE_ERROR_FAVORITE_CHARACTER_NETWORK))
            }
        }
    }
}