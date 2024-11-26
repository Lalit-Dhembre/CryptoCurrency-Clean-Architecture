package com.cosmicstruck.cryptocurrencycleanarchitecture.di

import com.cosmicstruck.cryptocurrencycleanarchitecture.common.BaseUrl
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.remote.CoinServiceApi
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.repository.CoinRepoImpl
import com.cosmicstruck.cryptocurrencycleanarchitecture.data.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApi() : CoinServiceApi{
        return Retrofit
            .Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api : CoinServiceApi) : CoinRepository{
        return CoinRepoImpl(api)
    }
}