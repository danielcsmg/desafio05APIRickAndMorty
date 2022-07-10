package br.com.zup.desafiorickemorty.ui.favoritecharacter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.domain.usecase.FavoriteCharacterListUseCase
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_LOAD_FAVORITE_CHARACTER
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteCharactersViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteCharacterListUseCase = FavoriteCharacterListUseCase(application)
    private val _favoriteCharactersListState = MutableLiveData<ViewState<List<CharacterResult>>>()
    val favoriteCharactersListState: LiveData<ViewState<List<CharacterResult>>> = _favoriteCharactersListState

    fun getFavoriteCharacters(){
      viewModelScope.launch{
          try {
              val response = withContext(Dispatchers.IO){
                  favoriteCharacterListUseCase.getFavoriteCharacters()
              }
              _favoriteCharactersListState.value = response
          }catch (e: Exception){
              ViewState.Error(Exception(MESSAGE_ERROR_LOAD_FAVORITE_CHARACTER))
          }
      }
    }
}