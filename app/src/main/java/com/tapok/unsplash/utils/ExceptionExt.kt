package com.tapok.unsplash.utils

import com.tapok.unsplash.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun HttpException.getHttpMessageId() = when (code()) {
    400 -> R.string.text_http_error_400
    401 -> R.string.text_http_error_401
    403 -> R.string.text_http_error_403
    404 -> R.string.text_http_error_404
    else -> R.string.text_http_error_500_503
}

fun Exception.getMessageId() = when (this) {
    is HttpException -> this.getHttpMessageId()
    is UnknownHostException -> R.string.text_host_error
    is SocketTimeoutException -> R.string.text_timeout_error
    else -> R.string.text_other_error
}
