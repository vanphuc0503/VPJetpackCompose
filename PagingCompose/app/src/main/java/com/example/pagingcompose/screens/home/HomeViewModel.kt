package com.example.pagingcompose.screens.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.pagingcompose.data.repository.Repository
import javax.inject.Inject

@ExperimentalPagingApi
class HomeViewModel @Inject constructor(
    repository: Repository
): ViewModel() {
    val getAllImages = repository.getAllImages()
}