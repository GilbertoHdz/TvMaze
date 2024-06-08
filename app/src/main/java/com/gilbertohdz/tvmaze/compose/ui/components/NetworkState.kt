package com.gilbertohdz.tvmaze.compose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.gilbertohdz.tvmaze.compose.utilities.network.ConnectionState
import com.gilbertohdz.tvmaze.compose.utilities.network.currentConnectivityState
import com.gilbertohdz.tvmaze.compose.utilities.network.observeConnectivityAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
private fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    // Creates a State<ConnectionState> with current connectivity state as initial value
    return produceState(initialValue = context.currentConnectivityState) {
        // In a coroutine, can make suspend calls
        context.observeConnectivityAsFlow().collect { value = it }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable
fun ConnectivityStatus(
    content: @Composable () -> Unit
) {
    val connection by connectivityState()

    val isConnected = connection === ConnectionState.Available

    if (isConnected) {
        content()
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "No Internet Connection",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
            )
        }
    }
}