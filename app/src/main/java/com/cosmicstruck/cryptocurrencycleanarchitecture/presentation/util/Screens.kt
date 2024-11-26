package com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.util

sealed class Screens(val route: String) {

    object HomeScreen : Screens("home_screen")
    object DetailScreen : Screens("detail_screen")
}