package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCancelAndConfirmButtons
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCheckBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultErrorDialog
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHeaderAdd
import com.rogue.financesrogue.ui.defaultComponentes.DefaultTextFieldToReceiveValues
import com.rogue.financesrogue.viewmodel.FixedValueViewModel
import org.koin.androidx.compose.koinViewModel

//v1 - 16/01/25
@Composable
fun FixedValuedUI() {
    val viewModel: FixedValueViewModel = koinViewModel()
    val paymentWayList by viewModel.paymentWayList.collectAsState()
    val categoryList by viewModel.categoryList.collectAsState()
    val personList by viewModel.personList.collectAsState()

    val paymentWay by viewModel.paymentWaySelected.collectAsState()
    val price by viewModel.price.collectAsState()
    val description by viewModel.description.collectAsState()

    val hasError by viewModel.hasError.collectAsState()
    val errorLog by viewModel.errorLog.collectAsState()

    var instantPrice by remember { mutableStateOf("") }

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
                .heightIn(max = 600.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    DefaultComboBox(
                        title = "Categoria:",
                        unselected = "Selecione a categoria",
                        items = categoryList,
                        onItemSelect = { viewModel.setCategory(it as CategoryEntity) },
                        canAdd = true,
                        onAdd = {
                            viewModel.onAddCategory(it)
                        }
                    )
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = "R$ $instantPrice",
                        label = "Valor:",
                        onlyNumbers = true
                    ) { input ->
                        val formatted = input.replace(Regex("[^0-9,.]"), "")
                        instantPrice = formatted

                        val numericValue = formatted.replace(",", ".").toDoubleOrNull() ?: 0.0
                        viewModel.setPrice(numericValue)
                    }
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = description,
                        label = "Descrição:",
                        maxLines = 4
                    ) {
                        viewModel.setDescription(it)
                    }
                }
                item {
                    DefaultComboBox(
                        title = "Forma de pagamento:",
                        unselected = "Selecione...",
                        items = paymentWayList,
                        onItemSelect = { viewModel.setPaymentWay(it as PaymentWayEntity) },
                        canAdd = true,
                        onAdd = { viewModel.addPaymentWay(it) }
                    )
                }
                if (paymentWay?.paymentWay.equals("Pessoa", ignoreCase = true)) {
                    item {
                        DefaultComboBox(
                            title = "Pagar para:",
                            unselected = "Selecione...",
                            items = personList,
                            onItemSelect = { viewModel.setPerson(it as PersonEntity) },
                            canAdd = true,
                            onAdd = { viewModel.addPerson(it) }
                        )
                    }
                }

                item {
                    DefaultCheckBox(
                        text = "Varíavel",
                        onCheckedChange = { viewModel.setIsVariable(it) },
                        explanationText = "Este valor existe todos os meses, " +
                                "mas pode variar. " +
                                "\n\nEx: Conta de água, Conta de Luz"
                    )
                }
                item {
                    DefaultCancelAndConfirmButtons {
                        viewModel.saveFixedValue()
                    }
                }
            }
        }
    }

    if (hasError){
        DefaultErrorDialog(
            title = "Algo está errado",
            message = errorLog,
            confirmButtonClicked = { viewModel.resetErrors() }
        )
    }
}

@Preview
@Composable
private fun FixedValueUIPrev() {
    FixedValuedUI()
}