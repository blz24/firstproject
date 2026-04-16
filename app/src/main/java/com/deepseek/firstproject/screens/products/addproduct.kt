package com.deepseek.firstproject.screens.products

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.deepseek.firstproject.R
import com.deepseek.firstproject.data.ProductViewModel


//AddProductscreen
//Scaffold-topbar
//Text-addproduct
//textfield-name,price,description,imagepicker
//button-addproduct
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Product") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan
                )
            )
        }
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var productname by remember { mutableStateOf("") }
            var price by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var imageUri by remember { mutableStateOf<Uri?>(null)}
            //image picker launcher
            val imagePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ){
                uri : Uri? ->
                imageUri = uri

        }
            Text(
                text = "Add Product",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Magenta
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = productname,
                onValueChange = { productname = it },
                label = { Text(text = "productname") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text(text = "enter product price") },
                modifier = Modifier.fillMaxWidth(0.9f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "enter product description") },
                modifier = Modifier.fillMaxWidth(0.9f)

            )
            Spacer(modifier = Modifier.height(20.dp))
            //Product image
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(140.dp)
                    .clickable {
                        imagePickerLauncher.launch("image/*")
                    }
            ){
                AsyncImage(
                    model = imageUri ?: R.drawable.deliver,
                    contentDescription = "product",
                    modifier = Modifier.size(140.dp),
                    contentScale = ContentScale.Crop
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(onClick = {imagePickerLauncher.launch("image/*")}){
                Text(text = "choose image")
            }


            Spacer(modifier = Modifier.height(20.dp))
            //
            val context = LocalContext.current
            val myproductViewModel = ProductViewModel(navController, context)

            Button(onClick = {
                myproductViewModel.uploadProduct(
                    imageUri = imageUri,
                    name = productname,
                    price = price,
                    description = description
                )
                //clear textfields
                productname = ""
                price = ""
                description = ""


            }, modifier = Modifier.fillMaxWidth(0.9f)) {
                Text(text = "ADD PRODUCT")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Addproductscreenpreview() {
    AddProductScreen(rememberNavController())
}
