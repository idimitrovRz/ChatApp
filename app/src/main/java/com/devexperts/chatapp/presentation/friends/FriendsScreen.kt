package com.devexperts.chatapp.presentation.friends

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devexperts.chatapp.presentation.Screen
import com.devexperts.chatapp.presentation.friends.components.FriendListItem

@Composable
fun FriendsScreen(
    navController: NavController,
    viewModel: FriendsViewModel
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.friends) { friend ->
                FriendListItem(
                    friend = friend,
                    onItemClick = {
                        navController.navigate(
                            Screen.ChatScreen.route + "/${friend.id}"
                        )
                    }
                )
                Divider()
            }
        }
    }
    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
//                .align(Alignment.Center)
        )
    }
    if (state.isLoading) {
        CircularProgressIndicator(
//            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colors.primary
        )
    }
}