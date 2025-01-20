package com.rogue.financesrogue.ui.screen.main.componentes

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogue.financesrogue.Nav

@Composable
fun MainFloatButton() {
    var expanded by remember { mutableStateOf(false) }

    val list = listOf(
        "Compra única",
        "Valor fixo",
        "Compra parcelada",
        "Valor a receber"
    )

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(16.dp)
    ) {
        list.forEachIndexed { index, item ->
            AnimatedVisibility(
                visible = expanded,
                enter = slideInVertically(
                    initialOffsetY = { it + (index * 50) },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300)),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeOut(animationSpec = tween(durationMillis = 300))
            ) {
                ItemFloatButton(
                    text = item,
                    onclick = {
                        expanded = false
                        when(item) {
                            "Compra única" -> Nav.navController?.navigate("addItemPurchased")
                            "Valor fixo" -> Nav.navController?.navigate("addFixedValue")
                            "Compra parcelada" -> Nav.navController?.navigate("addParcelValue")
                            "Valor a receber" -> Nav.navController?.navigate("addValueToReceive")
                        }
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = { expanded = !expanded },
            containerColor = Color.White,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Expandir opções",
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun ItemFloatButton(
    text: String,
    onclick: () -> Unit
) {
    Button(
        onClick = onclick,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = Modifier
            .padding(4.dp)
            .width(200.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun MainFloatButtonPrev() {
    MainFloatButton()
}

