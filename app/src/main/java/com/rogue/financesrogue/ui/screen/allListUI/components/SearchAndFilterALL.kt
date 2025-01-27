package com.rogue.financesrogue.ui.screen.allListUI.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogue.financesrogue.R
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCheckBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox

@Composable
fun SearchAndFilterBar() {
    var expanded by remember {
        mutableStateOf(false
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(25.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Pesquisar") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.padding(end = 15.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_icon),
                    contentDescription = null,
                    Modifier.size(25.dp)
                )
            }
        }
        AnimatedVisibility(visible = expanded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        DefaultCheckBox(text = "Compras únicas")
                        DefaultCheckBox(text = "Valores fixos")
                        DefaultCheckBox(text = "Compras parceladas")
                        DefaultCheckBox(text = "Valores a receber")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Column {
                        DefaultCheckBox(text = "Mês completo")
                        if (true){
                            DefaultComboBox(unselected = "Ano...")
                            DefaultComboBox(unselected = "Mês...")
                        } else {
                            TextField(
                                value = "",
                                onValueChange = {},
                                label = { Text(text = "Data inicial") },
                                modifier = Modifier.padding(5.dp)
                            )
                            TextField(
                                value = "",
                                onValueChange = {},
                                label = { Text(text = "Data final") },
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun SearchAndFilterBarPrev() {
    SearchAndFilterBar()
}