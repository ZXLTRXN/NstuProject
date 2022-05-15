package com.zxltrxn.nstuproject.features.parsing.commonDomain

sealed interface Resource<T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error<T>(val message: LocalizeString, val errorCode: Int) : Resource<T>
}

/*
error code
1 - network
2 - source unavailable
3 - parse error
0 - else
*/
