package com.rogue.financesrogue.ui.screen.main.componentes

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainFloatButton() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        containerColor = Color.White
        ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = Color.Black
        )
    }
}

@Preview
@Composable
private fun MainFloatButtonPrev() {
    MainFloatButton()
}