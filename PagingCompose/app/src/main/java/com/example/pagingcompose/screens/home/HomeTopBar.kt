package com.example.pagingcompose.screens.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pagingcompose.ui.theme.topAppBarBackgroundColor
import com.example.pagingcompose.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = "Home", color = MaterialTheme.colors.topAppBarContentColor
        )
    }, backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor, actions = {
        IconButton(onClick = onSearchClicked) {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "Search Icon"
            )
        }
    })
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar {}
}