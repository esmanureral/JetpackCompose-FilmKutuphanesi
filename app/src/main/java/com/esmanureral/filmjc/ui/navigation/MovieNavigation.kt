package com.esmanureral.filmjc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esmanureral.filmjc.ui.screen.HomeScreen

@Composable
fun MovieNavigation() {
    val navController= rememberNavController()

    NavHost(
        navController=navController,
        startDestination = MovieScreens.HomeScreen.name //Uygulama açıldığında gösterilecek başlangıç ekranı
    ){
        composable(MovieScreens.HomeScreen.name){
            HomeScreen(navController=navController)

        }
        composable(MovieScreens.DetailScreen.name){

        }
    }

    
}