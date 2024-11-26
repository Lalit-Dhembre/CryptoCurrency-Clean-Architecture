package com.cosmicstruck.cryptocurrencycleanarchitecture.domain.usecase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.cosmicstruck.cryptocurrencycleanarchitecture.common.Resource
import com.cosmicstruck.cryptocurrencycleanarchitecture.common.Resource.*
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.dto.toCoin
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.repository.CoinRepository
import com.cosmicstruck.cryptocurrencycleanarchitecture.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
){
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Loading())
            val coins = coinRepository.getCoinsList().map { it.toCoin() }
            emit(Success(data = coins))
        }catch (e : HttpException){
            emit(Resource.Error(messsage = e.localizedMessage ?: "An Unexpected Error Occured!"))
        }catch (e : IOException){
            emit(Resource.Error(messsage = e.localizedMessage ?: "An Unexpected Error occured!"))
        }
    }
}