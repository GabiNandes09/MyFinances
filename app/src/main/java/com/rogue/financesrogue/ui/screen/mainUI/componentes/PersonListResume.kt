package com.rogue.financesrogue.ui.screen.mainUI.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PersonListResume() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pessoas",
                fontSize = 25.sp
            )
            Button(
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Ver todos")
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
            }
        }
        LazyRow {
            for (i in 1..10){
                item {
                    PersonItem()
                }
            }
        }
    }
}

@Composable
private fun PersonItem() {
    Card(
        modifier = Modifier.padding(5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
            Text(text = "Nandes")
        }
    }
}

@Preview
@Composable
private fun PersonItemPrev() {
    PersonItem()
}

@Preview
@Composable
private fun PersonListResumePrev() {
    PersonListResume()
}