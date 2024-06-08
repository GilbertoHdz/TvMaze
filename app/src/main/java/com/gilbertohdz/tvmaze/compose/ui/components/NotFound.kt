package com.gilbertohdz.tvmaze.compose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.gilbertohdz.tvmaze.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NotFoundAndBackComponent(backAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(text = stringResource(R.string.not_found), style = MaterialTheme.typography.displayMedium)
            Button(onClick = backAction) {
                Text(text = stringResource(R.string.back_to_the_previous_screen))
            }
        }
    }
}


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NotFoundComponent(text: String = stringResource(R.string.not_found), modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = MaterialTheme.typography.displayMedium)
    }
}