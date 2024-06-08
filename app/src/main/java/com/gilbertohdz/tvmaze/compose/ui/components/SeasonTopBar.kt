package com.gilbertohdz.tvmaze.compose.ui.components


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SeasonTopBar(
    episodes: List<Int>,
    onClick: (Int) -> Unit
) {
    var focusedTabIndex by remember { mutableStateOf(0) }
    var selectedTabIndex by remember { mutableStateOf(focusedTabIndex) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions, doesTabRowHaveFocus ->

            // Focused
            TabRowDefaults.UnderlinedIndicator(
                currentTabPosition = tabPositions[focusedTabIndex],
                doesTabRowHaveFocus = doesTabRowHaveFocus,
            )

        },
        separator = { Spacer(modifier = Modifier.width(12.dp)) },
        modifier = Modifier
            .padding(start = 58.dp)
            .focusRestorer()
    ) {
        episodes.forEachIndexed { index, tab ->
            key(index) {
                Tab(
                    selected = index == selectedTabIndex,
                    onFocus = { focusedTabIndex = index },
                    onClick = {
                        focusedTabIndex = index
                        selectedTabIndex = index
                        onClick(tab)
                    }
                ) {
                    Text(
                        text = "Season $tab",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}
