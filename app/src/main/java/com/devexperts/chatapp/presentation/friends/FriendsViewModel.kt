package com.devexperts.chatapp.presentation.friends

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devexperts.chatapp.common.Resource
import com.devexperts.chatapp.domain.interactor.user.FriendsInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FriendsViewModel(
    private val friendsInteractor: FriendsInteractor
) : ViewModel() {

    private val _state = mutableStateOf(FriendsState())

    val state: State<FriendsState> = _state

    init {
        loadFriends()
    }

    private fun loadFriends() {
        friendsInteractor.getAll().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FriendsState(friends = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = FriendsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = FriendsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}