package com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.coinscreen

import com.cosmicstruck.cryptocurrencycleanarchitecture.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error : String = ""
)
