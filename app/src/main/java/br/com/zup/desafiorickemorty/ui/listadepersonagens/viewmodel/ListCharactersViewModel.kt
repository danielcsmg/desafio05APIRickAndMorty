package br.com.zup.desafiorickemorty.ui.listadepersonagens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.desafiorickemorty.data.model.CharacterResult
import br.com.zup.desafiorickemorty.domain.usecase.ListCharacterUseCase
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_FAVORITE_CHARACTER_NETWORK
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_LOAD_CHARACTER_NETWORK
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListCharactersViewModel(application: Application) : AndroidViewModel(application) {
    private val listCharactersUseCase = ListCharacterUseCase(application)
    private val _listCharactersState = MutableLiveData<ViewState<List<CharacterResult>>>()
    val listPersonagemState: LiveData<ViewState<List<CharacterResult>>> = _listCharactersState

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    listCharactersUseCase.getAllCharacterNetwork()
                }
                _listCharactersState.value = response
            }catch (e: Exception){
                _listCharactersState.value = ViewState.Error(
                    Throwable(MESSAGE_ERROR_LOAD_CHARACTER_NETWORK)
                )
            }
        }
    }

}