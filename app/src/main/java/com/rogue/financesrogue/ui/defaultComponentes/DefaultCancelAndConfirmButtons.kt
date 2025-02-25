package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.R

@Composable
fun DefaultCancelAndConfirmButtons(
    onSaveClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(50.dp),
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Button(
            onClick = { Nav.navController?.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Red)),
            modifier = Modifier.width(120.dp)
        ) {
            Text(
                text = "Cancelar",
                color = Color.White
            )
        }
        Button(
            onClick = { onSaveClick() },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Blue)),
            modifier = Modifier.width(120.dp)
        ) {
            Text(
                text = "Salvar",
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultCancelAndConfirmButtons {}
}