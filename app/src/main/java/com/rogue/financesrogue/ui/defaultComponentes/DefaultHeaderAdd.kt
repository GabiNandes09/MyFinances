package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.Nav

/**
 * Componente para cabeçalho de telas de adição.
 * title - Titulo que será exibido na tela, se não alterado irá mostrar nada.
 * explanationText - Se existir, mostrará um botão de ajuda, que deve explicar para que serve a tela
 */
@Composable
fun DefaultHeaderAdd(
    title: String = "",
    explanationText: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { Nav.navController?.popBackStack() },
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
        }
        Text(
            text = title,
            fontSize = 35.sp
        )
        explanationText?.let {
            DefaultHelpIconWithTooltip(
                it,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultHeaderAdd(
        title = "Teste de título",
        explanationText = "Temos um texto de explicação"
    )
}