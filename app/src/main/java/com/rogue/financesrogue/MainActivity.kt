package com.rogue.financesrogue

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rogue.financesrogue.database.MyFinancesDatabase
import com.rogue.financesrogue.database.dao.PaymentWayDAO
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.ui.screen.BasicRegistrationUI
import com.rogue.financesrogue.ui.screen.FixedValuedUI
import com.rogue.financesrogue.ui.screen.ItemPurchasedUI
import com.rogue.financesrogue.ui.screen.LoginUI
import com.rogue.financesrogue.ui.screen.ParcelValuesdUI
import com.rogue.financesrogue.ui.screen.RegisterUI
import com.rogue.financesrogue.ui.screen.ValuesToReceiveUI
import com.rogue.financesrogue.ui.screen.mainUI.MainUI
import com.rogue.financesrogue.ui.theme.MyFinancesTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinancesTheme {
                val navController = rememberNavController()
                val animationDuration = 600
                NavHost(
                    navController = navController,
                    startDestination = "login",
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(animationDuration)
                        ) + fadeIn(animationSpec = tween(300))
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(animationDuration)
                        ) + fadeOut(animationSpec = tween(300))
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(animationDuration)
                        ) + fadeOut(animationSpec = tween(300))
                    },
                    popEnterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(animationDuration)
                        ) + fadeIn(animationSpec = tween(300))
                    }
                ) {
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

