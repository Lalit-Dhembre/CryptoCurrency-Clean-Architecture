package com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.util

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.coinscreen.CoinDetailScreen
import com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.homescreen.CoinListScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavigationSetup(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(route = Screens.HomeScreen.route){
            CoinListScreen(navController = navHostController)
        }
        composable(route = Screens.DetailScreen.route + "/{coinId}"){
            CoinDetailScreen()
        }
    }
}