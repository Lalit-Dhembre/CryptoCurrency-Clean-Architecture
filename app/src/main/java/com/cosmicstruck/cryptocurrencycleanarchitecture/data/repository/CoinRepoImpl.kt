package com.cosmicstruck.cryptocurrencycleanarchitecture.data.repository

import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.CoinServiceApi
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.dto.CoinDetailDto
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.dto.CoinDto
import javax.inject.Inject

class CoinRepoImpl @Inject constructor(
    private val apiServiceApi: CoinServiceApi
) : CoinRepository{
    override suspend fun getCoinsList(): List<CoinDto> {
        return apiServiceApi.getCoinsList()
    }

    override suspend fun getCoinDetail(coinId: String): CoinDetailDto {
        return apiServiceApi.getCoin(coinId = coinId)
    }
}