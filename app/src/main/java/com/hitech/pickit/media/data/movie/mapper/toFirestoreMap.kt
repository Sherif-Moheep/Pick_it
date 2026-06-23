package com.hitech.pickit.media.data.movie.mapper

import com.google.firebase.firestore.FieldValue
import com.hitech.pickit.media.domain.model.Movie

// Convert Domain Movie -> Firestore Map (For Writing)
fun Movie.toFirestoreMap(): Map<String, Any?> {
    return hashMapOf(
        "id" to this.id,
        "name" to this.name,
        "overview" to this.overview,
        "posterUrl" to this.posterUrl,
        "backdropUrl" to this.backdropUrl,
        "releaseDate" to this.releaseDate,
        "voteAverage" to this.voteAverage,
        "voteCount" to this.voteCount,
        "genreIds" to this.genreIds,
        "savedAt" to FieldValue.serverTimestamp()
    )
}