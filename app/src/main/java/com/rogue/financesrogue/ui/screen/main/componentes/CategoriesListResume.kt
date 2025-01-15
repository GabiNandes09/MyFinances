package com.rogue.financesrogue.ui.screen.main.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//v1 - 15/01/2025
@Composable
fun CategoriesListResume() {
    Column(
        modifier = Modifier
            .background(Color.Gray)
            .heightIn(max = 300.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categorias",
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
    LazyColumn(
        modifier = Modifier
            .background(Color.Gray)
            .heightIn(max = 300.dp)
    ) {
        for (i in 1..5){
            item {
                CategoryItem()
            }
        }
    }
}

@Composable
private fun CategoryItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 2.5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
                )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Udemy",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Text(
                    text = "Subscription",
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
                )
            }
            Text(
                text = "R$ 29,90",
                modifier = Modifier.padding(horizontal = 15.dp)
                )
        }
    }
}

@Preview
@Composable
private fun CategoryItemPrev() {
    CategoryItem()
}

@Preview
@Composable
private fun CategoriesListResumePrev() {
    CategoriesListResume()
}