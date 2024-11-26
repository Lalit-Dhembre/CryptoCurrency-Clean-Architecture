package com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.homescreen

import com.cosmicstruck.cryptocurrencycleanarchitecture.domain.model.Coin

data class HomeScreenState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
