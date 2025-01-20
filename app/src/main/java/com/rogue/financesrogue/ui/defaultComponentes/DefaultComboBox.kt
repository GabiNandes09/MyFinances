package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DefaultComboBox(
    modifier: Modifier = Modifier,
    unselected: String = "Selecione..."
) {
    // Lista de itens do ComboBox
    val items = listOf("Opção 1", "Opção 2", "Opção 3", "Opção 4")
    var expanded by remember { mutableStateOf(false) } // Controla a exibição do menu
    var selectedItem by remember { mutableStateOf("") } // Item selecionado

    // Componente do ComboBox
    Box(
        modifier = modifier
    ) {
        // Caixa para exibição
        OutlinedTextField(
            value = selectedItem,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            onValueChange = { },
            label = { Text(unselected) },
            readOnly = true, // Evita edição direta
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    Modifier.clickable { expanded = !expanded } // Controla o estado de expansão
                )
            }
        )

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Fecha o menu ao clicar fora
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem = item // Atualiza o item selecionado
                        expanded = false // Fecha o menu
                    },
                    text = {Text(text = item)}
                )
            }
        }
    }
}

@Preview
@Composable
private fun ComboBoxExamplePrev() {
    DefaultComboBox()
}