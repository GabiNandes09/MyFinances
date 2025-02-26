package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.model.ValueType
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCancelAndConfirmButtons
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultErrorDialog
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHeaderAdd
import com.rogue.financesrogue.ui.defaultComponentes.DefaultTextFieldToReceiveValues
import com.rogue.financesrogue.viewmodel.ValueToReceiveViewModel
import org.koin.androidx.compose.koinViewModel

//v2 - 25/02/25
@Composable
fun ValuesToReceiveUI() {

    val viewModel: ValueToReceiveViewModel = koinViewModel()
    val personList by viewModel.personList.collectAsState()
    val price by viewModel.price.collectAsState()
    val description by viewModel.description.collectAsState()
    val parcels by viewModel.parcels.collectAsState()
    val type by viewModel.type.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val errorLog by viewModel.errorLog.collectAsState()

    var instantPrice by remember {
        mutableStateOf(price.toString())
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
                    DefaultComboBox(
                        title = "Receber de:",
                        unselected = "Selecione a pessoa...",
                        items = personList,
                        onItemSelect = { person ->
                            viewModel.setPerson(person as PersonEntity)
                        },
                        canAdd = true,
                        onAdd = { person ->
                            viewModel.onAddPerson(person)
                        }
                    )
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = "R$ $instantPrice",
                        label = "Valor Total:"
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
                        label = "Descrição:"
                    ) { newDescription ->
                        viewModel.setDescription(newDescription)
                    }
                }
                item {
                    DefaultComboBox(
                        title = "Tipo: ",
                        unselected = "Selecione tipo...",
                        items = ValueType.entries,
                        onItemSelect = { viewModel.setType(it as ValueType) }
                    )
                }
                if (type == ValueType.PARCEL) {
                    item {
                        DefaultTextFieldToReceiveValues(
                            value = if (parcels == null) "" else parcels.toString(),
                            label = "Total de parcelas:",
                        ) { parcels ->
                            viewModel.setParcels(
                                if (parcels.isEmpty()) null else parcels.toInt()
                            )
                        }
                    }
                }
                item {
                    DefaultCancelAndConfirmButtons {
                        viewModel.saveValueToReceive()
                    }
                }
            }
        }

        if (hasError) {
            DefaultErrorDialog(
                title = "Algo está errado",
                message = errorLog,
                confirmButtonClicked = { viewModel.resetError() }
            )
        }
    }
}

@Preview
@Composable
private fun ValuesToReceiveUIPrev() {
    ValuesToReceiveUI()
}