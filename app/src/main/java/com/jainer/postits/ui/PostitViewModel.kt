package com.jainer.postits.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jainer.postits.MainApplication
import com.jainer.postits.data.local.Postit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class PostitViewModel: ViewModel() {
    // referenciando o UiState da PostitScreen
    private val _postitUiState = MutableStateFlow(PostitUiState()) // versão mutável
    val postitUiState = _postitUiState.asStateFlow() // versão imutável para exposição

    // implementação da função de mudança do estado content
    init {
        _postitUiState.update {
            it.copy(
                onChangeContent = { content ->
                    _postitUiState.value = _postitUiState.value.copy(
                        content = content
                    )
                }
            )
        }
    }

    // obtém o DAO relacionado ao banco de dados
    val postitDao = MainApplication.postitDatabase.getPostitDao()
    // coleta todos os post-its armazenados no banco de dados
    val postitList = postitDao.getAllPostit()

    // adiciona um novo post-it ao banco de dados com o conteúdo informado e a data e hora atuais
    fun addPostit(content: String) {
        /*
        * Conceito de corrotinas em Kotlin:
        * Executa esta etapa em uma linha de execução (thread) diferente para não afetar
        * a linha de execução principal e o aplicativo por consequência
        * */
        viewModelScope.launch(Dispatchers.IO) {
            postitDao.addPostit(
                Postit(
                    content = content,
                    createdAt = Date.from(Instant.now())
                )
            )
        }
    }

    // deleta o post-it indicado do banco de dados
    fun deletePostit(postit: Postit) {
        // o mesmo conceito de corrotina se aplica na execução deste bloco de código
        viewModelScope.launch(Dispatchers.IO) {
            postitDao.deletePostit(postit)
        }
    }
}