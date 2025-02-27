package com.rogue.financesrogue.ui.defaultComponentes


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultDatePicker(
    modifier: Modifier = Modifier,
    date: String? = "",
    onDateSelected: (LocalDate) -> Unit
) {
    var openDatePicker by remember { mutableStateOf(false) }
    val state = rememberDatePickerState()

    Column(
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp)),
            readOnly = true,
            value = date ?: "",
            onValueChange = {},
            label = { Text(text = "Data:") },
            interactionSource = remember {
                MutableInteractionSource()
            }.also {
                LaunchedEffect(key1 = it) {
                    it.interactions.collectLatest { interaction ->
                        if (interaction is PressInteraction.Release) {
                            openDatePicker = true
                        }
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black
            )
        )
    }

    AnimatedVisibility(visible = openDatePicker) {
        DatePickerDialog(
            onDismissRequest = { openDatePicker = false },
            confirmButton = {
                Button(onClick = {
                    state.selectedDateMillis?.let { millis ->
                        val localDateTime = Instant.ofEpochMilli(millis)
                            .atZone(ZoneOffset.UTC)
                            .toLocalDate()
                        onDateSelected(localDateTime)
                    }
                    openDatePicker = false
                }
                ) {
                    Text(text = "Selecionar")
                }
            }
        ) {
            DatePicker(state)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Prev() {
    DefaultDatePicker {}
}