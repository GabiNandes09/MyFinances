package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultCheckBox(text: String) {
    var checked by remember {
        mutableStateOf(false)
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {checked = !checked}
        )
        Text(text = text)
    }
}

@Preview
@Composable
private fun DefaultCheckBoxPrev() {
    DefaultCheckBox("Isso é um teste")
}