package com.devexperts.chatapp.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devexperts.chatapp.common.Constants
import com.devexperts.chatapp.common.Resource
import com.devexperts.chatapp.domain.interactor.message.MessageInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChatViewModel(
    private val messagesInteractor: MessageInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ChatState())

    val state: State<ChatState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_FRIEND_ID)?.let { friendId ->
            loadChat(friendId)
        }
    }

    private fun loadChat(friendId: String) {
        messagesInteractor.getMessagesByUserId(friendId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ChatState(messages = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ChatState(
                        error =  result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ChatState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}