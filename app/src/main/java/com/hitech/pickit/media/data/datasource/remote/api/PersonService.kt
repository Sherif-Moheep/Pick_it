package com.hitech.pickit.media.data.datasource.remote.api

import com.hitech.pickit.media.data.datasource.remote.dto.PersonDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonService {
    @GET("3/person/{personId}")
    suspend fun getPerson(@Path("personId") personId: String): PersonDTO
}
