package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

//import androidx.navigation.NavHost

@Composable
fun Navigation(viewModel: WishViewModel= androidx.lifecycle.viewmodel.compose.viewModel(),
               navController: NavHostController = rememberNavController()){
    NavHost(
        navController=navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(Screen.HomeScreen.route){
            HomeView(navController,viewModel)
        }
        composable(Screen.AddScreen.route+ "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ){
            entry->
            val id = if(entry.arguments != null)  entry.arguments!!.getInt("id") else 0
            AddEditDetailView(id = id, viewModel = viewModel , navController = navController)
        }
    }
}