package com.example.pagingcompose.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.pagingcompose.model.UnsplashImage
import com.example.pagingcompose.navigation.Screen
import com.example.pagingcompose.screens.common.ListContent

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalPagingApi
@Composable
fun HomeScreen(
    navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()
) {
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()
    val refreshing by homeViewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing, {
        homeViewModel.refresh()
    })

    HomeContent(
        onSearchClicked = {
            navController.navigate(Screen.Search.route)
        },
        getAllImages = getAllImages,
        pullRefreshState,
        refreshing
    )
}

@Composable
@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
private fun HomeContent(
    onSearchClicked: () -> Unit,
    getAllImages: LazyPagingItems<UnsplashImage>,
    pullRefreshState: PullRefreshState,
    refreshing: Boolean,
) {
    Scaffold(topBar = {
        HomeTopBar(
            onSearchClicked = onSearchClicked
        )
    }, content = {
        Box(modifier = Modifier.pullRefresh(pullRefreshState).pullRefreshIndicatorTransform(pullRefreshState)) {
            ListContent(items = getAllImages, it)
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    })
}