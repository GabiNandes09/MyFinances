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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rogue.financesrogue.model.User
import com.rogue.financesrogue.repositories.UserDAO
import com.rogue.financesrogue.ui.screen.FixedValuedUI
import com.rogue.financesrogue.ui.screen.ItemPurchasedUI
import com.rogue.financesrogue.ui.screen.LoginUI
import com.rogue.financesrogue.ui.screen.ParcelValuesdUI
import com.rogue.financesrogue.ui.screen.RegisterUI
import com.rogue.financesrogue.ui.screen.ValuesToReceiveUI
import com.rogue.financesrogue.ui.screen.main.MainUI
import com.rogue.financesrogue.ui.theme.MyFinancesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinancesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginUI()
                    }
                    composable("main") {
                        MainUI()
                    }
                    composable("addItemPurchased") {
                        ItemPurchasedUI()
                    }
                    composable("addFixedValue") {
                        FixedValuedUI()
                    }
                    composable("addParcelValue") {
                        ParcelValuesdUI()
                    }
                    composable("addValueToReceive") {
                        ValuesToReceiveUI()
                    }
                    composable("register") {
                        RegisterUI()
                    }
                }
                Nav.navController = navController
            }
        }
    }
}

