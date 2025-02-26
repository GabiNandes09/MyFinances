package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultCheckBox(
    text: String,
    onCheckedChange: (Boolean) -> Unit,
    explanationText: String = ""
) {
    var checked by remember {
        mutableStateOf(false)
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                onCheckedChange(it)
                checked = !checked
            },
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.Gray
            )
        )
        Text(text = text)

        if (explanationText.isNotEmpty()){
            DefaultHelpIconWithTooltip(
                explanationText = explanationText
            )
        }
    }
}

@Preview
@Composable
private fun DefaultCheckBoxPrev() {
    DefaultCheckBox(
        text = "Isso é um teste",
        onCheckedChange = {}
        )
}