package com.hitech.pickit.auth.data.repository

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hitech.pickit.auth.data.util.Constants.WEB_CLIENT_ID
import com.hitech.pickit.auth.data.mapper.toDomainModel
import com.hitech.pickit.auth.domain.model.UserData
import com.hitech.pickit.auth.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthRepository {

    override fun getSignedInUser(): UserData? {
        val currentUser = auth.currentUser
        return currentUser?.toDomainModel()
    }

    override suspend fun signInWithGoogle(context: Context): Result<UserData> {
        return try {
            // 1. Get the Token from Google (The UI Popup happens here)
            val credentialManager = CredentialManager.create(context)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(WEB_CLIENT_ID)
                .setAutoSelectEnabled(true)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val credential = result.credential

            // 2. Extract Token
            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val googleToken = googleIdTokenCredential.idToken

                // 3. Login to Firebase
                val authCredential = GoogleAuthProvider.getCredential(googleToken, null)
                val authResult = auth.signInWithCredential(authCredential).await()
                val user = authResult.user

                if (user != null) {
                    Result.success(
                        user.toDomainModel()
                    )
                } else {
                    Result.failure(Exception("User is null"))
                }
            } else {
                Result.failure(Exception("No Google Credential found"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // e.g. User cancelled
            Result.failure(e)
        }
    }

    override suspend fun signOut(context: Context) {
        try {
            // Sign out of Firebase
            auth.signOut()

            // Clear Google Credential State (So the account picker shows up next time)
            val credentialManager = CredentialManager.create(context)
            credentialManager.clearCredentialState(ClearCredentialStateRequest())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}