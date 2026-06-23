package com.hitech.pickit.media.data.tvshow.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.hitech.pickit.media.domain.model.TVShow

fun DocumentSnapshot.toTVShow(): TVShow {
    return TVShow(
        id = (this.get("id") as? Long)?.toInt() ?: 0,
        name = this.getString("name") ?: "",
        overview = this.getString("overview") ?: "",
        posterUrl = this.getString("posterUrl"),
        backdropUrl = this.getString("backdropUrl"),
        releaseDate = this.getString("firstAirDate"),
        voteAverage = this.getDouble("voteAverage") ?: 0.0,
        voteCount = (this.get("voteCount") as? Long)?.toInt() ?: 0,
        genreIds = (this.get("genreIds") as? List<Long>)?.map { it.toInt() } ?: emptyList()
    )
}