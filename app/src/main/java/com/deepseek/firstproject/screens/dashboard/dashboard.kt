package com.deepseek.firstproject.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.deepseek.firstproject.data.AuthViewModel
import com.deepseek.firstproject.navigation.ROUTE_ADD_PRODUCT
import com.deepseek.firstproject.navigation.ROUTE_LISTPRODUCT
import com.deepseek.firstproject.navigation.ROUTE_LOGIN
import com.deepseek.firstproject.navigation.ROUTE_MYINTENTS
import com.deepseek.firstproject.screens.Homescreen.HomeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(navController: NavHostController){
    val context = LocalContext.current
    val myauth = AuthViewModel(navController, context)
    Scaffold(
        // topbar
        topBar = {
            TopAppBar(
                title = { Text("Welcome to my App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD0BCFF),
                    titleContentColor = Color.Blue
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                    IconButton(onClick = { myauth.logout() }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        },
        // bottom bar
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("HOME") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        },
        // floating action button
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(ROUTE_ADD_PRODUCT) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Product")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var username by remember { mutableStateOf("loading...") }
            LaunchedEffect(Unit) {
                myauth.getCurrentUserName {
                    username = it
                }
            }
            Text(text = "Welcome $username")
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                HomeCard(title = "ADD Product", background = Color.Blue, onClick = {
                    navController.navigate(ROUTE_ADD_PRODUCT)
                })
                HomeCard(title = "Update Product", background = Color.Magenta, onClick = {})
            }
            Row {
                HomeCard(title = "Product List", background = Color.Gray, onClick = {navController.navigate(
                    ROUTE_LISTPRODUCT
                )})
                HomeCard(title = "my Intents", background = Color.Red, onClick = {navController.navigate(
                    ROUTE_MYINTENTS
                )})
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview(){
    DashBoardScreen(rememberNavController())
}
