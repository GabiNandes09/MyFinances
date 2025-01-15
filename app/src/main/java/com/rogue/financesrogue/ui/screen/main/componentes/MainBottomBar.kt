package com.rogue.financesrogue.ui.screen.main.componentes

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainBottomBar() {
    val items = listOf(
        BottomItem("Resumo", Icons.Default.Home)
    )
    BottomAppBar(actions = {
        items.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(text = item.label) }
            )
        }
    })
}

@Preview
@Composable
private fun MainBottomBarPrev() {
    MainBottomBar()
}

data class BottomItem(
    val label: String,
    val icon: ImageVector
)