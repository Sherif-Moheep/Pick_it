package com.hitech.pickit.core.presentation.utils

import android.content.Context
import com.hitech.pickit.R
import com.hitech.pickit.core.domain.utils.NetworkError

fun NetworkError.asString(context: Context): String {
    val resId = when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.too_many_requests
        NetworkError.NO_INTERNET -> R.string.no_internet
        NetworkError.SERVER_ERROR -> R.string.unknow_error
        NetworkError.SERIALIZATION -> R.string.serialization_error
        NetworkError.UNKNOW -> R.string.unknow_error
    }
    return context.getString(resId)
}