package com.rogue.financesrogue.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.collectAsState
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
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.ui.defaultComponentes.DefaultCancelAndConfirmButtons
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultDatePicker
import com.rogue.financesrogue.ui.defaultComponentes.DefaultErrorDialog
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHeaderAdd
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHelpIconWithTooltip
import com.rogue.financesrogue.ui.defaultComponentes.DefaultTextFieldToReceiveValues
import com.rogue.financesrogue.viewmodel.ItemPurchasedViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

//v1 - 16/01/25
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemPurchasedUI() {
    val viewModel: ItemPurchasedViewModel = koinViewModel()
    val paymentWayList by viewModel.paymentWayList.collectAsState()
    val categoryList by viewModel.categoryList.collectAsState()
    val personList: List<PersonEntity> by viewModel.personList.collectAsState(emptyList())

    val pw by viewModel.paymentWay.collectAsState()
    val value by viewModel.value.collectAsState()
    val description by viewModel.description.collectAsState()
    val date by viewModel.date.collectAsState()
    val hasError by viewModel.hasError.collectAsState()

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    var instantValue by remember {
        mutableStateOf(value.toString())
    }

    if (hasError) {
        DefaultErrorDialog(
            title = "Campos necessários",
            message = "É necessário preencher todos os campos",
            confirmButtonClicked = { viewModel.errorOkay() },
            onDismissRequest = { viewModel.errorOkay() }
        )
    }

    Scaffold(
        topBar = {
            DefaultHeaderAdd(
                title = "Compra única",
                explanationText = "Destinado a compras que ocorrem pontualmente em seu mês, " +
                        "e não necessáriamente acontecerão sempre. \n" +
                        "Podemos ter como exemplo: \n" +
                        "* Compras de lanches \n" +
                        "* Gastos em passeios \n" +
                        "* Combustivel \n" +
                        "* Um presente \n"
            )
        },
        containerColor = Color.Gray
    ) { paddingValues ->
        Card(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    Column(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Text(text = "Categoria:")
                        DefaultComboBox(
                            canAdd = true,
                            unselected = "Selecione a categoria",
                            items = categoryList,
                            onItemSelect = {
                                viewModel.setCategory(it as CategoryEntity)
                            },
                            type = "categoria",
                            onAdd = { description ->
                                viewModel.onAddCategory(description)
                            }
                        )
                    }
                }
                item {
                    DefaultTextFieldToReceiveValues(
                        value = "R$ $instantValue",
                        label = "Valor:"
                    ) { input ->
                        val formatted = input.replace(Regex("[^0-9,.]"), "")
                        instantValue = formatted

                        val numericValue = formatted.replace(",", ".").toDoubleOrNull() ?: 0.0
                        viewModel.setValue(numericValue)
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
                    Column(
                        modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
                    ) {
                        DefaultDatePicker(date = date?.format(formatter)) {
                            viewModel.setDate(it)
                        }
                    }
                }
                item {
                    Column {
                        Text(text = "Forma de pagamento:")
                        DefaultComboBox(
                            unselected = "Selecione...",
                            items = paymentWayList,
                            onItemSelect = {
                                viewModel.setPaymentWay(it as PaymentWayEntity)
                            },
                            canAdd = true,
                            type = "forma de pagamento",
                            onAdd = {
                                viewModel.addPaymentWay(it)
                            }
                        )
                    }
                }

                if (pw?.paymentWay.equals("Pessoa", ignoreCase = true)) {
                    item {
                        Column(
                            modifier = Modifier.padding(top = 10.dp)
                        ) {
                            Text(text = "Pagar para:")
                            DefaultComboBox(
                                items = personList,
                                unselected = "Selecione...",
                                onItemSelect = {
                                    viewModel.setPerson(it as PersonEntity)
                                },
                                canAdd = true,
                                type = "pessoa",
                                onAdd = {
                                    viewModel.addPerson(it)
                                }
                            )
                        }
                    }
                }

                item {
                    DefaultCancelAndConfirmButtons {
                        viewModel.saveItem()
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun ItemPurchasedUIPrev() {
    ItemPurchasedUI()
}