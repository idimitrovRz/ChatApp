package com.devexperts.chatapp.presentation.chat

import com.devexperts.chatapp.data.local.message.Message

data class ChatState(
    val isLoading: Boolean = false,
    val messages: List<Message> = emptyList(),
    val error: String = ""
)
