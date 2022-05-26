package com.zxltrxn.nstuproject.features.parsing.commonDomain

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val message: LocalizeString, val errorCode: ErrorCode? = null) :
        Resource<Nothing>
}

enum class ErrorCode(val code: Int) {
    NETWORK(1),
    SOURCE_UNAVAILABLE(2),
    PARSE(3),
    SERIALIZATION(4),
    UNKNOWN(0)
}

fun <T, R> Resource<T>.map(block: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Error -> this
        is Resource.Success -> Resource.Success(data = block(this.data))
    }
}
