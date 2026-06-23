package com.hitech.pickit.core.di

import android.content.Context
import androidx.room.Room
import com.hitech.pickit.core.data.datasource.local.local.MovieDao
import com.hitech.pickit.core.data.datasource.local.local.TMDbDatabase
import com.hitech.pickit.core.data.datasource.local.local.TVShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideTMDbDatabase(@ApplicationContext context: Context): TMDbDatabase = Room
        .databaseBuilder(
            context,
            TMDbDatabase::class.java,
            "PickIt.db",
        ).build()

    @Provides
    @Singleton
    fun provideMovieDao(db: TMDbDatabase): MovieDao = db.movieDao

    @Provides
    @Singleton
    fun provideTVShowDao(db: TMDbDatabase): TVShowDao = db.tvShowDao
}
