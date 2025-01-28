package com.rogue.financesrogue.ui.screen.mainUI.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.viewmodel.MainPageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainBottomBar(
    selectedItem: Int,
    viewModel: MainPageViewModel
) {
    val items by remember {
        mutableStateOf(viewModel.bottomBarItens)
    }

    BottomAppBar(actions = {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedItem == items.indexOf(item),
                onClick = { viewModel.chanceSelectedTo(items.indexOf(item)) },
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(text = item.label) }
            )
        }
    })
}

@Preview
@Composable
private fun MainBottomBarPrev() {
    val viewModel: MainPageViewModel = koinViewModel()
    MainBottomBar(
        0,
        viewModel
    )
}

data class BottomItem(
    val label: String,
    val icon: ImageVector
)