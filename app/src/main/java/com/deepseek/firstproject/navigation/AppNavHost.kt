package com.deepseek.firstproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deepseek.firstproject.screens.dashboard.DashBoardScreen
import com.deepseek.firstproject.screens.login.LoginScreen
import com.deepseek.firstproject.screens.products.AddProductScreen
import com.deepseek.firstproject.screens.products.ProductListScreen
import com.deepseek.firstproject.screens.products.UpdateProductScreen
import com.deepseek.firstproject.profile.ProfileScreen
import com.deepseek.firstproject.screens.register.RegisterScreen
import com.deepseek.firstproject.screens.splashscreen.SplashScreen
import com.deepseek.firstproject.screens.demo.IntentScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_DASHBOARD) {
            DashBoardScreen(navController)
        }
        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUTE_ADD_PRODUCT) {
            AddProductScreen(navController)
        }
        composable(ROUTE_MYINTENTS) {
            IntentScreen(navController)
        }
        composable(ROUTE_LISTPRODUCT) {
            ProductListScreen(navController)
        }
        composable( ROUTE_UPDATEPRODUCT + "/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")!!
            UpdateProductScreen(navController, productId)
        }
        composable(ROUTE_PROFILE) {
            ProfileScreen(navController)
        }

    }
}
