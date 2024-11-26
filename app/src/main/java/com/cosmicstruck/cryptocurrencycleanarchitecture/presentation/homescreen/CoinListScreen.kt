package com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.homescreen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.homescreen.components.CoinListItem
import com.cosmicstruck.cryptocurrencycleanarchitecture.presentation.util.Screens

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewmodel: HomeScreenViewmodel = hiltViewModel()
){
    val state = viewmodel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(if(isSystemInDarkTheme()) Color.Black else Color.White)) {
            items(state.coins) { coin->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navController.navigate(Screens.DetailScreen.route + "/${coin.id}")
                    })
                }
            }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        }
    }
