package com.hitech.pickit.auth.domain.repository

import android.content.Context
import com.hitech.pickit.auth.domain.model.UserData

interface AuthRepository {
    suspend fun signInWithGoogle(context: Context): Result<UserData>
    suspend fun signOut(context: Context)
    fun getSignedInUser(): UserData?
}