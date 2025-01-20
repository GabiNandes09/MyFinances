package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.rogue.financesrogue.R

@Composable
fun DefaultHelpIconWithTooltip(
    explanationText: String,
    modifier: Modifier = Modifier
) {
    val showTooltip = remember { mutableStateOf(false) }

    Box(modifier = Modifier) {
        // Icone de interrogação
        IconButton(
            onClick = { showTooltip.value = !showTooltip.value },
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.help),
                contentDescription = "Ajuda"
            )
        }

        // Popup com a explicação
        if (showTooltip.value) {
            Popup(
                alignment = Alignment.TopCenter,
                onDismissRequest = { showTooltip.value = false },
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                        .widthIn(max = 200.dp)
                ) {
                    Text(
                        text = explanationText,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DefafultQuestionButton() {
    DefaultHelpIconWithTooltip("")
}