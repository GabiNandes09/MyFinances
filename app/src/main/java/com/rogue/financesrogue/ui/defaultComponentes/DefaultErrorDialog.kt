package com.rogue.financesrogue.ui.defaultComponentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.R

@Composable
fun DefaultErrorDialog(
    title: String,
    message: String,
    confirmButtonClicked: () -> Unit
) {
    AlertDialog(
        containerColor = Color.LightGray,
        onDismissRequest = { confirmButtonClicked() },
        confirmButton = {
            Row(
                modifier = Modifier.width(350.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { confirmButtonClicked() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.Blue)
                    )
                ) {
                    Text(
                        text = "ENTENDIDO",
                        color = Color.White
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                modifier = Modifier
                    .width(350.dp)
                    .padding(10.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = message,
                modifier = Modifier
                    .width(350.dp)
                    .padding(bottom = 25.dp, start = 10.dp, end = 10.dp),
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    )
}

@Preview
@Composable
private fun Prev() {
    DefaultErrorDialog(
        title = "Título",
        message = "Isso é um teste",
        {}
    )
}