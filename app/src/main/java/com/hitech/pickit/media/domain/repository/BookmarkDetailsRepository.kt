package com.hitech.pickit.media.domain.repository

import com.hitech.pickit.media.presentation.models.TMDbItem

interface BookmarkDetailsRepository<T : TMDbItem> {
    suspend fun addBookmark(item: T)

    suspend fun deleteBookmark(id: Int)

    suspend fun isBookmarked(id: Int): Boolean

    suspend fun getBookmarks(): List<T>

    fun isUserLoggedIn(): Boolean
}