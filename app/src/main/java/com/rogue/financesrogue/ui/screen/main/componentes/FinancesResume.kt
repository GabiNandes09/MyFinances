package com.rogue.financesrogue.ui.screen.main.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.R

//v3 - 15/01/2025
@Composable
fun FinancesResume() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Resumo",
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Total: ",
                    fontSize = 18.sp
                )
                Text(
                    text = "R$ 852,00",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.Blue)
                )
            }
            Spacer(modifier = Modifier
                .padding(bottom = 5.dp)
                .height(0.5.dp)
                .fillMaxWidth()
                .background(Color.Black))
            ItemRow(title = "Salário atual", value = 2500.0)
            ItemRow(title = "Valores a receber", value = 800.0)
            ItemRow(title = "Contas fixas", value = -375.0)
            ItemRow(title = "Contas parceladas", value = -200.0)
            ItemRow(title = "Compras pontuais", value = -987.0)


        }
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
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
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