package com.hitech.pickit.media.data.tvshow.mapper

import com.google.firebase.firestore.FieldValue
import com.hitech.pickit.media.domain.model.TVShow

fun TVShow.toFirestoreMap(): Map<String, Any?> {
    return hashMapOf(
        "id" to this.id,
        "name" to this.name,
        "overview" to this.overview,
        "posterUrl" to this.posterUrl,
        "backdropUrl" to this.backdropUrl,
        "firstAirDate" to this.releaseDate,
        "voteAverage" to this.voteAverage,
        "voteCount" to this.voteCount,
        "genreIds" to this.genreIds,
        "savedAt" to FieldValue.serverTimestamp()
    )
}