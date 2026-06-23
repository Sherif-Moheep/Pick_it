package com.hitech.pickit.media.data.movie.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.hitech.pickit.media.domain.model.Movie

// Convert Firestore Document -> Domain Movie (For Reading)
fun DocumentSnapshot.toMovie(): Movie {
    return Movie(
        id = (this.get("id") as? Long)?.toInt() ?: 0,
        name = this.getString("name") ?: "",
        overview = this.getString("overview") ?: "",
        posterUrl = this.getString("posterUrl"),
        backdropUrl = this.getString("backdropUrl"),
        releaseDate = this.getString("releaseDate"),
        voteAverage = this.getDouble("voteAverage") ?: 0.0,
        voteCount = (this.get("voteCount") as? Long)?.toInt() ?: 0,
        // Handle List<Int> conversion
        genreIds = (this.get("genreIds") as? List<Long>)?.map { it.toInt() } ?: emptyList()
    )
}