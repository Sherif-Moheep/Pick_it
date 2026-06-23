package com.hitech.pickit.media.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Popular

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NowPlaying

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Latest

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TopRated

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Trending

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Discover

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Search

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Similar

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AiringToday
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Upcoming
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class OnTheAir
