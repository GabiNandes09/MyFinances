package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.R

@Composable
fun LoginUI() {
    Scaffold(
        containerColor = colorResource(id = R.color.grayBlack)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_my_finances),
                contentDescription = null
            )
            Text(
                text = "MY FINANCES",
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 50.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Username",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(250.dp)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Password",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(250.dp)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.padding(vertical = 15.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                Text(
                    text = "Esqueci minha senha",
                    color = Color.LightGray
                )
            }
            Button(
                onClick = { Nav.navController?.navigate("main") },
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Login",
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "Criar conta",
                    color = Color.LightGray
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginPrev() {
    LoginUI()
}