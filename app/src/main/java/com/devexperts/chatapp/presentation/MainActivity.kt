package com.devexperts.chatapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devexperts.chatapp.data.local.AppDatabase
import com.devexperts.chatapp.data.local.user.User
import com.devexperts.chatapp.domain.interactor.user.FriendsInteractor
import com.devexperts.chatapp.presentation.bottom_navigation.BottomNavigationBar
import com.devexperts.chatapp.presentation.bottom_navigation.BottomNavigationItem
import com.devexperts.chatapp.presentation.chat.ChatScreen
import com.devexperts.chatapp.presentation.friends.FriendsScreen
import com.devexperts.chatapp.presentation.friends.FriendsViewModel
import com.devexperts.chatapp.presentation.ui.theme.ChatAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            ChatAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavigationItem(
                                    name = "Messages",
                                    route = Screen.FriendsScreen.route,
                                    icon = Icons.Default.Home
                                ),
                                BottomNavigationItem(
                                    name = "Settings",
                                    route = Screen.SettingsScreen.route,
                                    icon = Icons.Default.Settings
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(
                            PaddingValues(
                                start = 0.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = paddingValues.calculateBottomPadding()
                            )
                        )
                    ) {
                        Surface(color = MaterialTheme.colors.background) {
                            NavHost(
                                navController = navController,
                                startDestination = Screen.FriendsScreen.route
                            ) {
                                composable(
                                    Screen.FriendsScreen.route
                                ) {
                                    FriendsScreen(
                                        navController = navController,
                                        viewModel = FriendsViewModel(
                                            friendsInteractor = FriendsInteractor(
                                                userDao = AppDatabase.getDatabase(applicationContext)
                                                    .userDao()
                                            )
                                        )
                                    )
                                }
                                composable(
                                    Screen.ChatScreen.route + "/{friendId}"
                                ) {
                                    ChatScreen(navController)
                                }
                                composable(
                                    Screen.SettingsScreen.route
                                ) {
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}