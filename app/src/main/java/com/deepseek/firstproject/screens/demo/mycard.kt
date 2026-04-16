package com.deepseek.firstproject.screens.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deepseek.firstproject.R

@Composable
fun MyCardExample() {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(150.dp), // Set proper height
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Image fills the card, preserves aspect ratio
            Image(
                painter = painterResource(id = R.drawable.frequency),
                contentDescription = "Frequency Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Text overlay centered
            Text(
                text = "Hello",
                color = Color.White
            )
        }
    }
}

@Composable
fun CardRowExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MyCardExample()
        MyCardExample()
        MyCardExample()
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyCardExample()
        Spacer(modifier = Modifier.height(16.dp))
        CardRowExample()
    }
}