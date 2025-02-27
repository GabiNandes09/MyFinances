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
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCancelAndConfirmButtons
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCheckBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHeaderAdd
import com.rogue.financesrogue.ui.defaultComponentes.DefaultTextFieldToReceiveValues
import com.rogue.financesrogue.viewmodel.ParcelValueViewModel
import org.koin.androidx.compose.koinViewModel

//v1 - 16/01/25
@Composable
fun ParcelValuesdUI() {
    val viewModel: ParcelValueViewModel = koinViewModel()

    val categoryList by viewModel.categoryList.collectAsState()
    val personList by viewModel.personList.collectAsState()

    val price by viewModel.price.collectAsState()
    val description by viewModel.description.collectAsState()
    val payForPerson by viewModel.payForPerson.collectAsState()

    var instantPrice by remember { mutableStateOf(price.toString()) }
    var instantParcels by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            DefaultHeaderAdd(
                title = "Compra parcelada",
                explanationText = "Compras parceladas, que serão cobradas todos os meses por x meses" +
                        "\n\nExemplo: Móvel divído em 10x, Tênis divído em 6x"
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
                        title = "Categoria:",
                        unselected = "Selecione a categoria",
                        items = categoryList,
                        onItemSelect = { viewModel.setCategory(it as CategoryEntity) },
                        canAdd = true,
                        onAdd = { viewModel.onAddCategory(it) }
                    )
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = "R$ $instantPrice",
                        label = "Valor total:"
                    ) { input ->
                        val formatted = input.replace(Regex("[^0-9,.]"), "")
                        instantPrice = formatted

                        val numericValue = formatted.replace(",", ".").toDoubleOrNull() ?: 0.0
                        viewModel.setPrice(numericValue)
                    }
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = instantParcels,
                        label = "Total de parcelas:"
                    ) {
                        instantParcels = it
                        val parcels = it.toIntOrNull()
                        parcels?.let { viewModel.setParcels(parcels) }
                    }
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = description,
                        label = "Descrição:"
                    ) {
                        viewModel.setDescription(it)
                    }
                }

                item {
                    DefaultCheckBox(
                        text = "Pagar para pessoa",
                        onCheckedChange = { viewModel.setPayForPerson(it) }
                    )
                }
                if (payForPerson) {
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
                    DefaultCancelAndConfirmButtons {
                        viewModel.saveParcelValue()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ParcelValuesUIPrev() {
    ParcelValuesdUI()
}