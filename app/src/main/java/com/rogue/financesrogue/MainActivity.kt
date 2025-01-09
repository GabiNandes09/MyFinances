package com.rogue.financesrogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogue.financesrogue.model.User
import com.rogue.financesrogue.repositories.UserDAO
import com.rogue.financesrogue.ui.screen.LoginUI
import com.rogue.financesrogue.ui.theme.MyFinancesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinancesTheme {
                LoginUI()
            }
        }
    }
}

@Composable
fun Greeting() {
    val userDao = UserDAO(LocalContext.current)
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var salary by remember {
        mutableStateOf("")
    }

    var list by remember { mutableStateOf(userDao.selectAll()) }

    LaunchedEffect(key1 = list) {
        list = userDao.selectAll()
    }
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                modifier = Modifier.padding(10.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                modifier = Modifier.padding(10.dp)
            )
            TextField(
                value = salary,
                onValueChange = { salary = it },
                label = { Text(text = "Salary") },
                modifier = Modifier.padding(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                onClick = {
                    userDao.insertOne(
                        User(
                            0,
                            username,
                            password,
                            salary.toDouble()
                        )
                    )
                    list = userDao.selectAll()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Salvar")
            }

            LazyColumn {
                items(list){item ->
                    val user = item as User
                    Item(user)
                }
            }
        }
    }
}

@Composable
fun Item(user: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(15.dp)
    ){
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "ID: ${user.userId}")
            Text(text = "Username: ${user.username}")
            Text(text = "Password: ${user.password}")
            Text(text = "Salary: ${user.salary}")
        }
    }
}

@Preview
@Composable
private fun ItemPrev() {
    Item(
        User(
            1,
            "Nome teste",
            "13456",
            1500.00
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFinancesTheme {
        Greeting()
    }
}