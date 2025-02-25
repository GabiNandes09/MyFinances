package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.R
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCheckBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHeaderAdd
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHelpIconWithTooltip
import com.rogue.financesrogue.ui.defaultComponentes.DefaultTextFieldToReceiveValues

//v2 - 25/02/25
@Composable
fun ValuesToReceiveUI() {

    var parcelEnable by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            DefaultHeaderAdd(
                title = "Valor a receber",
                explanationText = "Valores que você deve receber, geralmente de outra pessoa\n" +
                        "Podemos utilizar como exemplo um valor emprestado, ou seu salário, alguma devolução."
            )
        },
        containerColor = Color.Gray
    ) { paddingValues ->
        Card(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    Column(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Text(text = "Receber de:")
                        DefaultComboBox(
                            unselected = "Selecione a pessoa"
                        )
                    }
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = "",
                        label = "Valor Total:"
                    ) {

                    }
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = "",
                        label = "Descrição:"
                    ) {

                    }
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = if (parcelEnable) "" else "1",
                        label = "Total de parcelas:",
                        enable = parcelEnable
                    ) {

                    }
                }
                item {
                    DefaultCheckBox(
                        text = "Pagamento único",
                        onCheckedChange = { checked ->
                            parcelEnable = !checked
                        }
                    )
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(50.dp),
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Red)),
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text(
                                text = "Cancelar",
                                color = Color.White
                            )
                        }
                        Button(
                            onClick = { /*TODO*/ },
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
            }
        }
    }
}

@Preview
@Composable
private fun ValuesToReceiveUIPrev() {
    ValuesToReceiveUI()
}