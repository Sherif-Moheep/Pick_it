package com.hitech.pickit.core.di

import com.hitech.pickit.BuildConfig
import com.hitech.pickit.media.data.datasource.remote.api.MovieService
import com.hitech.pickit.media.data.datasource.remote.api.PersonService
import com.hitech.pickit.media.data.datasource.remote.api.TVShowService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton



@Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            val clientBuilder = OkHttpClient.Builder()

                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)


            clientBuilder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header(
                        "Authorization",
                        "Bearer ${BuildConfig.TMDB_BEARER_TOKEN}"
                    )
                    .header("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }

            if (BuildConfig.DEBUG) {
                clientBuilder.addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }

            return clientBuilder.build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }

            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .client(okHttpClient)
                .addConverterFactory(
                    json.asConverterFactory("application/json".toMediaType())
                )
                .build()
        }


    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)


    @Singleton
    @Provides
    fun provideTVShowService(retrofit: Retrofit): TVShowService =
        retrofit.create(TVShowService::class.java)

    @Singleton
    @Provides
    fun providePersonService(retrofit: Retrofit): PersonService =
        retrofit.create(PersonService::class.java)
}