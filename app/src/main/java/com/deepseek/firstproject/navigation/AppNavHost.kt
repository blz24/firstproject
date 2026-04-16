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
import com.deepseek.firstproject.screens.register.RegisterScreen
import com.deepseek.firstproject.screens.splashscreen.SplashScreen

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
            MyIntentsScreen(navController)
        }
        composable(ROUTE_LISTPRODUCT) {
            ProductListScreen(navController)
        }

    }
}

@Composable
fun MyIntentsScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}
