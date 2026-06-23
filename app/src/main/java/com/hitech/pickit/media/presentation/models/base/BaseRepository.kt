package com.hitech.pickit.media.presentation.models.base

import com.hitech.pickit.core.data.networking.safeApiCall
import com.hitech.pickit.core.domain.utils.NetworkError
import com.hitech.pickit.core.domain.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository<T>(
    private val ioDispatcher: CoroutineDispatcher
) {


    protected abstract suspend fun getSuccessResult(id: Any?): T
    fun getResult(id: Any?): Flow<Result<T, NetworkError>> = flow {


        val result = safeApiCall {
            getSuccessResult(id)
        }

        emit(result)

    }.flowOn(ioDispatcher)
}