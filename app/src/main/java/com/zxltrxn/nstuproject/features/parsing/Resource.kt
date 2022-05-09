package com.zxltrxn.nstuproject.features.parsing

sealed class Resource<T>(val data: T? = null, val errorCode: Int? = null){
    class Success<T>(data: T):Resource<T>(data)
    class Error<T>(errorCode: Int, data:T? = null):Resource<T>(data, errorCode)
}

/*
error code
1 - network
2 - source unavailable
3 - parse error
0 - else
*/
