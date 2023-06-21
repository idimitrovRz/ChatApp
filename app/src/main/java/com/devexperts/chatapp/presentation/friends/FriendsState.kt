package com.devexperts.chatapp.presentation.friends

import com.devexperts.chatapp.data.local.user.User

data class FriendsState(
    val isLoading: Boolean = false,
    val friends: List<User> = emptyList(),
    val error: String = ""
)
