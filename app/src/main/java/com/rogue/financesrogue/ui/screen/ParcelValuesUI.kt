package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.R
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHelpIconWithTooltip

//v1 - 16/01/25
@Composable
fun ParcelValuesdUI() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { Nav.navController?.popBackStack() },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                Text(
                    text = "Compra parcelada",
                    fontSize = 35.sp
                )
                DefaultHelpIconWithTooltip(
                    "",
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        },
        containerColor = Color.Gray
    ) { paddingValues ->
        Card(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    Column(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Text(text = "Categoria:")
                        DefaultComboBox(
                            unselected = "Selecione a categoria"
                        )
                    }
                }
                item {
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(vertical = 5.dp),
                        label = { Text(text = "Valor total:") },
                        shape = RoundedCornerShape(25.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        singleLine = true
                    )
                }
                item {
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(vertical = 5.dp),
                        label = { Text(text = "Total de parcelas:") },
                        shape = RoundedCornerShape(25.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        singleLine = true
                    )
                }
                item {
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(vertical = 5.dp),
                        label = { Text(text = "Descrição:") },
                        shape = RoundedCornerShape(25.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        maxLines = 4
                    )
                }

                item{
                    Column {
                        Text(text = "Forma de pagamento:")
                        DefaultComboBox(
                            unselected = "Selecione..."
                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Text(text = "Pagar para:")
                        DefaultComboBox(
                            unselected = "Selecione..."
                        )
                    }
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(50.dp),
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Red)),
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text(
                                text = "Cancelar",
                                color = Color.White
                            )
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Blue)),
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text(
                                text = "Salvar",
                                color = Color.White
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
private fun ParcelValuesUIPrev() {
    ParcelValuesdUI()
}