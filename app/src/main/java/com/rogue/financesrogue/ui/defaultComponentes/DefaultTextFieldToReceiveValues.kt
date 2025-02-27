package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Componente para padronizar TextFields de inserção de dados
 * @param value Valor inserido pelo usuário
 * @param label Label para mostrar o que deve ser inserido ali
 */
@Composable
fun DefaultTextFieldToReceiveValues(
    value: String,
    label: String,
    enable: Boolean = true,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        readOnly = readOnly,
        onValueChange = {
            onValueChange(it)
        },
        enabled = enable,
        maxLines = maxLines,
        modifier = Modifier.padding(vertical = 5.dp),
        label = {
            Text(
                text = label,
                color = Color.LightGray
            )
        },
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Gray
        ),
        singleLine = true
    )
}

@Preview
@Composable
private fun Preview() {
    DefaultTextFieldToReceiveValues(
        value = "Este é o valor inserido",
        onValueChange = {},
        label = "Este é o valor mostrado:"
    )
}