package com.devexperts.chatapp.presentation

sealed class Screen(val route: String) {
    object FriendsScreen: Screen("friends_screen")
    object ChatScreen: Screen("chat_screen")
    object SettingsScreen: Screen("settings_screen")
}