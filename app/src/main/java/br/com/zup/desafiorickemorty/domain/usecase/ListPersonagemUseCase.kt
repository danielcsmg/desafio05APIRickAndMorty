package br.com.zup.desafiorickemorty.domain.usecase

import android.app.Application
import br.com.zup.desafiorickemorty.data.model.PersonagemResult
import br.com.zup.desafiorickemorty.domain.repository.PersonagemRepository
import br.com.zup.desafiorickemorty.ui.MESSAGE_ERROR_LOAD_CHARACTER_NETWORK
import br.com.zup.desafiorickemorty.ui.viewstate.ViewState

class ListPersonagemUseCase(application: Application) {
    private val personagemRepository = PersonagemRepository()

    suspend fun getAllPersonagensNetwork(): ViewState<List<PersonagemResult>>{
        return try {
            val response = personagemRepository.getAllPersonagensNetwork()
            ViewState.Success(response.results)
        }catch (e: Exception){
            ViewState.Error(Exception(MESSAGE_ERROR_LOAD_CHARACTER_NETWORK))
        }
    }
}