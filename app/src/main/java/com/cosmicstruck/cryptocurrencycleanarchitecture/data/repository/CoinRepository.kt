package com.cosmicstruck.cryptocurrencycleanarchitecture.data.repository

import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.dto.CoinDetailDto
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoinsList() : List<CoinDto>

    suspend fun getCoinDetail(coinId : String) : CoinDetailDto
}