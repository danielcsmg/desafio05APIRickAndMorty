package br.com.zup.desafiorickemorty.ui.listadepersonagens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.desafiorickemorty.data.model.PersonagemResult
import br.com.zup.desafiorickemorty.domain.model.SingleLiveEvent
import br.com.zup.desafiorickemorty.domain.usecase.ListPersonagemUseCase
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_LOAD_CHARACTER_NETWORK
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListPersonagensViewModel(application: Application) : AndroidViewModel(application) {
    val listPersonagemUseCase = ListPersonagemUseCase(application)
    private val _listPersonagemState = SingleLiveEvent<ViewState<List<PersonagemResult>>>()
    val listPersonagemState = _listPersonagemState

    fun getAllPersonagens() {
        viewModelScope.launch {
            try {
                val response = withContext((Dispatchers.IO)) {
                    listPersonagemUseCase.getAllPersonagensNetwork()
                }
                _listPersonagemState.value = response
            }catch (e: Exception){
                _listPersonagemState.value = ViewState.Error(
                    Throwable(MESSAGE_ERROR_LOAD_CHARACTER_NETWORK)
                )
            }
        }
    }
}