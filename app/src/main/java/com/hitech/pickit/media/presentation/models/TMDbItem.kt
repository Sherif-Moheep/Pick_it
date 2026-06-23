package com.hitech.pickit.media.presentation.models



interface TMDbItem  {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterUrl: String?
    val backdropUrl: String?
    val name: String
    val voteAverage: Double
    val voteCount: Int
    val genreIds : List<Int>
}
