package com.devexperts.chatapp.presentation

sealed class Screen(val route: String) {
    object MessagesScreen: Screen("messages_screen")
    object ChatScreen: Screen("chat_screen")
    object SettingsScreen: Screen("settings_screen")
}