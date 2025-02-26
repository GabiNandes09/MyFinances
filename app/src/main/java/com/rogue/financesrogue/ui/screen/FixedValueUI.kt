package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.R
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCancelAndConfirmButtons
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCheckBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHeaderAdd
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHelpIconWithTooltip
import com.rogue.financesrogue.ui.defaultComponentes.DefaultTextFieldToReceiveValues

//v1 - 16/01/25
@Composable
fun FixedValuedUI() {
    Scaffold(
        containerColor = Color.Gray,
        topBar = {
            DefaultHeaderAdd(
                title = "Valor fixo",
                explanationText = "Valores que são cobrados todos os meses, " +
                        "independentemente de compras," +
                        "podendo ou não variar." +
                        "\n \n Exemplo: Conta de água, luz, internet, Streamings, Academia."
            )

        }
    ) { paddingValues ->
        Card(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
                .height(600.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    DefaultComboBox(
                        title = "Categoria:",
                        unselected = "Selecione a categoria"
                    )
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = "",
                        label = "Valor:"
                    ) {

                    }
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = "",
                        label = "Descrição:",
                        maxLines = 4
                    ) {

                    }
                }
                item {
                    DefaultComboBox(
                        title = "Forma de pagamento:",
                        unselected = "Selecione..."
                    )
                }
                item {
                    DefaultComboBox(
                        title = "Pagar para:",
                        unselected = "Selecione..."
                    )
                }
                item {
                    DefaultCheckBox(
                        text = "Varíavel",
                        onCheckedChange = {},
                        explanationText = "Este valor existe todos os meses, " +
                                "mas pode variar. " +
                                "\n\nEx: Conta de água, Conta de Luz"
                    )
                }
                item {
                    DefaultCancelAndConfirmButtons {

                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FixedValueUIPrev() {
    FixedValuedUI()
}