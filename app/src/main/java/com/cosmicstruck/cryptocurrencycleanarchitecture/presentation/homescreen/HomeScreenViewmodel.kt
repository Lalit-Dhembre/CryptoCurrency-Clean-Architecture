package com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.homescreen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmicstruck.cryptocurrencycleanarchitecture.common.Resource
import com.cosmicstruck.cryptocurrencycleanarchitecture.domain.usecase.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class HomeScreenViewmodel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
): ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinUseCase().onEach { result->
            when(result){
                is Resource.Error -> {
                    _state.value = HomeScreenState(error = result.msg ?: "Unexpected Error")
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HomeScreenState(coins = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}