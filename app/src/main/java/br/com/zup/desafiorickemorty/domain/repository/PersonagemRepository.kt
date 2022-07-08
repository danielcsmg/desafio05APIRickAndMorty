package br.com.zup.desafiorickemorty.domain.repository

import br.com.zup.desafiorickemorty.data.datasource.remote.RetroftService
import br.com.zup.desafiorickemorty.data.model.PersonagensResponse

class PersonagemRepository {
    suspend fun getAllPersonagensNetwork(): PersonagensResponse{
        return RetroftService.apiService.getAllPersonagensNetwork()
    }
}