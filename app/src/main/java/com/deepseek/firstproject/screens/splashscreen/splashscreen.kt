package com.deepseek.firstproject.screens.splashscreen

import android.R.attr.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import com.deepseek.firstproject.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay
import kotlin.time.Duration.Companion.seconds



@Composable
fun SplashScreen(navController: NavHostController){
    LaunchedEffect(key1 = true){
        delay(2000)
        navController.navigate("ROUTE_LOGIN")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.company),
                    contentDescription = "company",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
            Spacer(modifier = Modifier.height(20.dp))
                Text(text= "Welcome to my app",
                    fontSize = 28.sp,
                    color = Color.Red)

        }
    }
}
@Preview(showBackground = true)
@Composable
fun splashscreenpreview(){
     SplashScreen(rememberNavController())
}
