package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.R
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox

@Composable
fun CreditCardUI() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(Color.LightGray)
    ) {
        Text(
            text = "Cartão de crédito",
            modifier = Modifier.padding(10.dp),
            fontSize = 25.sp
            )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(5.dp),
            label = {
                Text(
                    text = "Apelido:",
                    color = Color.Black
                )
            },
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
        DefaultComboBox(
            unselected = "Selecione a marca",
            modifier = Modifier.padding(5.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(5.dp),
            label = {
                Text(
                    text = "Data de fechamento:",
                    color = Color.Black
                )
            },
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Red))
            ) {
                Text(
                    text = "Cancelar",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Blue))
            ) {
                Text(
                    text = "Gravar",
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreditCardUIPrev() {
    CreditCardUI()
}