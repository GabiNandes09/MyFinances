package com.rogue.financesrogue.ui.screen.main.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.R

@Composable
fun FinancesResume() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Resumo",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            fontSize = 20.sp
        )
        ItemRow(title = "Salário atual", value = 2500.0)
        ItemRow(title = "Valores a receber", value = 800.0)
        ItemRow(title = "Contas fixas", value = -375.0)
        ItemRow(title = "Contas parceladas", value = -200.0)
        ItemRow(title = "Compras pontuais", value = -987.0)

        Spacer(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .background(Color.Black)
                .height(2.dp)
                .fillMaxWidth()

        )
        ItemRow(title = "Total", value = 987.0)
    }
}

@Composable
private fun ItemRow(
    title: String,
    value: Double
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$title: ",
            color = Color.Black
        )
        Text(
            text = "R$ $value",
            color = if (value >= 0.0)
                colorResource(id = R.color.Blue)
            else colorResource(id = R.color.Red)
        )
    }
}

@Preview
@Composable
private fun FinancesResumePrev() {
    FinancesResume()
}