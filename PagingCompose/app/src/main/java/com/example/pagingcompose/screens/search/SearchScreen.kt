package com.example.pagingcompose.screens.search

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.pagingcompose.model.UnsplashImage
import com.example.pagingcompose.screens.common.ListContent
import com.example.pagingcompose.screens.home.SearchWidget

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalPagingApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val searchedImages = searchViewModel.searchedImages.collectAsLazyPagingItems()

    SearchContent(
        searchQuery,
        onTextChange = {
            searchViewModel.updateSearchQuery(query = it)
        },
        onSearchClicked = {
            searchViewModel.searchHeroes(query = it)
        },
        onCloseClicked = { navController.popBackStack() },
        searchedImages
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
private fun SearchContent(
    searchQuery: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
    searchedImages: LazyPagingItems<UnsplashImage>
) {
    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = { onTextChange.invoke(it) },
                onSearchClicked = { onSearchClicked.invoke(it) },
                onCloseClicked = onCloseClicked
            )
        },
        content = {
            ListContent(items = searchedImages, it)
        }
    )
}