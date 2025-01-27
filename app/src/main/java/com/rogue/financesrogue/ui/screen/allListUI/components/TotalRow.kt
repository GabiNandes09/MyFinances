package com.rogue.financesrogue.ui.screen.allListUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogue.financesrogue.R

@Composable
fun TotalRow() {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(25.dp))
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Total gasto:",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "R$ 1500,00",
            modifier = Modifier.padding(10.dp),
            color = colorResource(id = R.color.Blue)
        )
    }
}

@Preview
@Composable
private fun TotalRowPrev() {
    TotalRow()
}