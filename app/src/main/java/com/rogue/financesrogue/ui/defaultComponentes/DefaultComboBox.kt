package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.ui.screen.BasicRegistrationUI
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DefaultComboBox(
    modifier: Modifier = Modifier,
    unselected: String = "Selecione...",
    items: List<Any> = emptyList(),
    onItemSelect: (Any) -> Unit = {},
    canAdd: Boolean = false,
    type: String = "Falta preencher",
    onAdd: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var showAdd by remember { mutableStateOf(false) }

    if (showAdd) {
        Dialog(onDismissRequest = { showAdd = false }) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BasicRegistrationUI(
                    column = type,
                    onCancelClick = { showAdd = false },
                    onSaveClick = {
                        onAdd(it)
                        showAdd = false
                    }
                )
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        TextField(
            value = selectedItem,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            onValueChange = { },
            label = {
                Text(
                    unselected,
                    modifier = Modifier.background(Color.Transparent)
                )
            },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    Modifier.clickable { expanded = !expanded }
                )
            },
            interactionSource = remember {
                MutableInteractionSource()
            }.also {
                LaunchedEffect(key1 = it) {
                    it.interactions.collectLatest { interaction ->
                        if (interaction is PressInteraction.Release) {
                            expanded = true
                        }
                    }
                }
            }
        )

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            if (canAdd) {
                DropdownMenuItem(
                    onClick = {
                        showAdd = true
                    },
                    text = {
                        Text(
                            text = "Adicionar novo",
                            color = Color.Black
                        )
                    },
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp)
                )
            }
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem = item.toString()
                        expanded = false
                        onItemSelect(item)
                    },
                    text = {
                        Text(
                            text = item.toString(),
                            color = Color.Black
                        )
                    },
                    modifier = Modifier
                        .padding(2.dp)
                        .width(200.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ComboBoxExamplePrev() {
    DefaultComboBox(type = "Teste")
}