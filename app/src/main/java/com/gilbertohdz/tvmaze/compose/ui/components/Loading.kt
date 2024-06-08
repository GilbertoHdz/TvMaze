package com.gilbertohdz.tvmaze.compose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.gilbertohdz.tvmaze.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = stringResource(R.string.loading), style = MaterialTheme.typography.displayMedium)
    }
}
