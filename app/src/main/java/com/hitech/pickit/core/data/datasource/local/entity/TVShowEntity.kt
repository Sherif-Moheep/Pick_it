package com.hitech.pickit.core.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hitech.pickit.media.domain.model.TVShow
import com.hitech.pickit.media.presentation.utili.db_converters.Converters

@Entity(tableName = "TVShows")
@TypeConverters(Converters::class)
class TVShowEntity(
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

fun List<TVShowEntity>.asDomainModel() = map(TVShowEntity::asDomainModel)

fun TVShow.asDatabaseModel(): TVShowEntity = TVShowEntity(
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

private fun TVShowEntity.asDomainModel(): TVShow = TVShow(
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
