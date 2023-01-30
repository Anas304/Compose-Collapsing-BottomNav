package com.example.collapsablebottomnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoriesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Categories Screen",
            style = TextStyle(color = Color.Black, fontSize = 10.sp),
            textAlign = TextAlign.Center)
    }
}
@Composable
fun CartScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Cart Screen",
            style = TextStyle(color = Color.Black, fontSize = 10.sp),
            textAlign = TextAlign.Center)
    }
}
@Composable
fun AccountScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Account Screen",
            style = TextStyle(color = Color.Black, fontSize = 10.sp),
            textAlign = TextAlign.Center)
    }
}


@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    // Describes a padding to be applied along the edges inside a box
    LazyColumn(contentPadding = innerPadding) {
        // Repeat a single ItemView with 20 rows
        items(count = 20) {
            ItemView()
        }
    }
}
@Composable
private fun ItemView() {
    // Add the ItemView Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            // Within this Column scope, add a child layout a Row with an image
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    rememberVectorPainter(image = Icons.Rounded.Done),
                    contentDescription = "A dummy image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                // Within this Row scope, add a child layout - two Columns each with a dummy text
                Column {
                    Text(
                        text = "This is a sample title",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = "This is simply a dummy text. Let's create a collapsible Bottom Navigation using Jetpack Compose Navigation",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}