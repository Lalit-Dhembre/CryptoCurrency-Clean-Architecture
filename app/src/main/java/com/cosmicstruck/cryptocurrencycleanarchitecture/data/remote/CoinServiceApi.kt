package com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote

import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.dto.CoinDetailDto
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.dto.CoinDto
import com.cosmicstruck.cryptocurrencycleanarchitecture.domain.model.Coin
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinServiceApi {

    @GET("/v1/coins")
    suspend fun getCoinsList() : List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinId : String) : CoinDetailDto
}