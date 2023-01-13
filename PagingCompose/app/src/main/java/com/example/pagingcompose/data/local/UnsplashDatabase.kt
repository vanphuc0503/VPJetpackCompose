package com.example.pagingcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagingcompose.data.local.dao.UnsplashImageDao
import com.example.pagingcompose.data.local.dao.UnsplashRemoteKeysDao
import com.example.pagingcompose.model.UnsplashImage
import com.example.pagingcompose.model.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao

}