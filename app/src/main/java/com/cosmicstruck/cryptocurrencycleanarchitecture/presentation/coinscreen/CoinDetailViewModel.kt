package com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.coinscreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmicstruck.cryptocurrencycleanarchitecture.common.PARAM_ID_COIN
import com.cosmicstruck.cryptocurrencycleanarchitecture.common.Resource
import com.cosmicstruck.cryptocurrencycleanarchitecture.domain.usecase.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val saveStateHandle : SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        saveStateHandle.get<String>(PARAM_ID_COIN)?.let { coinId ->
            Log.d("COIN ID", "$coinId")
            getCoinDetails(coinId)
        }
    }

    private fun getCoinDetails(coinId: String){
        getCoinDetailUseCase(coinId = coinId).onEach {result->
            when(result){
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.msg ?: "Unexpected Error")
                    Log.d("Coin Details Error","${result.msg}")

                }
                is Resource.Loading ->{
                    _state.value = CoinDetailState(isLoading = true)
                    Log.d("Coin Details Loading","${result.data}")

                }
                is Resource.Success -> {
                    _state.value = CoinDetailState(coinDetail = result.data)
                    Log.d("Coin Details","${result.data}")
                }
            }
        }.launchIn(viewModelScope)
    }
}