package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.R

@Composable
fun BasicRegistrationUI(
    column: String,
    onCancelClick: () -> Unit,
    onSaveClick: (String) -> Unit
) {
    var description by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastrar $column",
            modifier = Modifier.padding(10.dp),
            fontSize = 25.sp
        )
        TextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = { Text(text = "$column:") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier
                .padding(15.dp),
            shape = RoundedCornerShape(25.dp),
            readOnly = false
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Button(
                onClick = { onCancelClick() },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Red))
            ) {
                Text(
                    text = "Cancelar",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Button(
                onClick = { onSaveClick(description) },
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
private fun BasicRegistrationUIPrev() {
    BasicRegistrationUI(
        column = "Teste",
        {},
        {}
    )
}