package com.rogue.financesrogue.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.lifecycle.ViewModel
import com.rogue.financesrogue.ui.screen.mainUI.componentes.BottomItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainPageViewModel : ViewModel() {

    val bottomBarItens = listOf(
    BottomItem("Resumo", Icons.Default.Home),
    BottomItem("Compras", Icons.Default.ShoppingCart)
    )

    private val _selectedItem = MutableStateFlow(0)
    val selectedItem = _selectedItem.asStateFlow()

    fun chanceSelectedTo(index: Int){
        _selectedItem.value = index
    }
}