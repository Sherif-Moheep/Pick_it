package com.hitech.pickit.media.data.movie.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.hitech.pickit.media.data.movie.mapper.toFirestoreMap
import com.hitech.pickit.media.data.movie.mapper.toMovie
import com.hitech.pickit.media.domain.repository.BookmarkDetailsRepository
import com.hitech.pickit.media.domain.model.Movie
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreBookmarkMovieDetailsRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : BookmarkDetailsRepository<Movie> {

    private fun getUserBookmarksCollection() =
        auth.currentUser?.uid?.let { uid ->
            firestore
                .collection("users")
                .document(uid)
                .collection("bookmarks_movies")
        }

    override suspend fun addBookmark(item: Movie) {
        val collection = getUserBookmarksCollection() ?: return

        collection.document(item.id.toString())
            .set(item.toFirestoreMap())
            .await()
    }

    override suspend fun deleteBookmark(id: Int) {
        val collection = getUserBookmarksCollection() ?: return
        collection.document(id.toString()).delete().await()
    }

    override suspend fun isBookmarked(id: Int): Boolean {
        val collection = getUserBookmarksCollection() ?: return false
        val snapshot = collection.document(id.toString()).get().await()
        return snapshot.exists()
    }

    override suspend fun getBookmarks(): List<Movie> {
        val collection = getUserBookmarksCollection() ?: return emptyList()

        val snapshot = collection
            .orderBy("savedAt", Query.Direction.DESCENDING)
            .get()
            .await()

        return snapshot.documents.map { it.toMovie() }
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}