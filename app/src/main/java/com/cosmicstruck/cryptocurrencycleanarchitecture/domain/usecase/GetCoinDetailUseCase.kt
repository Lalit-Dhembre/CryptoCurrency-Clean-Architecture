package com.cosmicstruck.cryptocurrencycleanarchitecture.domain.usecase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.cosmicstruck.cryptocurrencycleanarchitecture.common.Resource
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.dto.toCoinDetail
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.repository.CoinRepository
import com.cosmicstruck.cryptocurrencycleanarchitecture.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
){
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(coinId: String) : Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinDetail(coinId).toCoinDetail()
            emit(Resource.Success(data = coinDetail))

        }catch (e: HttpException){
            emit(Resource.Error(messsage = e.localizedMessage ?: "An Unexpected Error Occured"))
        }catch (e: IOException){
            emit(Resource.Error(messsage = e.localizedMessage ?: "An Unexpected Error Occured"))
        }
    }
}