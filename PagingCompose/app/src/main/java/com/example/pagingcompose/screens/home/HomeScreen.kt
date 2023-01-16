package com.example.pagingcompose.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.pagingcompose.model.UnsplashImage
import com.example.pagingcompose.navigation.Screen
import com.example.pagingcompose.screens.common.ListContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalPagingApi
@Composable
fun HomeScreen(
    navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()
) {
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    HomeContent(
        onSearchClicked = {
            navController.navigate(Screen.Search.route)
        },
        getAllImages = getAllImages
    )
}

@Composable
@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
private fun HomeContent(
    onSearchClicked: () -> Unit,
    getAllImages: LazyPagingItems<UnsplashImage>,
) {
    Scaffold(topBar = {
        HomeTopBar(
            onSearchClicked = onSearchClicked
        )
    }, content = {
        ListContent(items = getAllImages, it)
    })
}