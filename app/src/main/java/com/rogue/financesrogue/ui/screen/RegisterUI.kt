package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//v1 - 17/01/2025
@Composable
fun RegisterUI() {
    Scaffold(
        topBar = { TitleHead() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Usuário: ") },
                modifier = Modifier.padding(vertical = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Nome: ") },
                modifier = Modifier.padding(vertical = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Sobrenome: ") },
                modifier = Modifier.padding(vertical = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Salário: ") },
                modifier = Modifier.padding(vertical = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Senha: ") },
                modifier = Modifier.padding(vertical = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Confirmar senha: ") },
                modifier = Modifier.padding(vertical = 10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Registrar")
            }
        }
    }
}

@Composable
private fun TitleHead() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Registrar",
            fontSize = 30.sp
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 15.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@Composable
private fun RegisterUIPrev() {
    RegisterUI()
}