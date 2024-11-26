package com.cosmicstruck.cryptocurrencycleanarchitecture.common

sealed class Resource<T>(
    val data : T? = null,
    val msg : String? = null
){
    class Success<T>(data: T) : Resource<T>(data)

    class Error<T>(data: T? = null, messsage: String) : Resource<T>(data,messsage)

    class Loading<T>(data: T? = null) : Resource<T>(data)
}
