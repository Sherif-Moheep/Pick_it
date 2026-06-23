package com.hitech.pickit.core.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hitech.pickit.media.domain.model.Movie
import com.hitech.pickit.media.presentation.utili.db_converters.Converters


@Entity(tableName = "Movies")
@TypeConverters(Converters::class)
class MovieEntity(
    @PrimaryKey val id: Int,
    val overview: String,
    val releaseDate: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int,
    val genreIds: List<Int>
)

fun List<MovieEntity>.asDomainModel() = map(MovieEntity::asDomainModel)

fun Movie.asDatabaseModel(): MovieEntity = MovieEntity(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
    genreIds = genreIds
)

private fun MovieEntity.asDomainModel(): Movie = Movie(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
    genreIds = genreIds

)
