package com.rogue.financesrogue.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogue.financesrogue.Nav
import com.rogue.financesrogue.R
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.ui.defaultComponentes.DefaultComboBox
import com.rogue.financesrogue.ui.defaultComponentes.DefaultDatePicker
import com.rogue.financesrogue.ui.defaultComponentes.DefaultHelpIconWithTooltip
import com.rogue.financesrogue.viewmodel.ItemPurchasedViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

//v1 - 16/01/25
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemPurchasedUI() {
    val viewModel: ItemPurchasedViewModel = koinViewModel()
    val paymentWayList by viewModel.paymentWayList.collectAsState()
    val categoryList by viewModel.categoryList.collectAsState()
    val personList by viewModel.personList.collectAsState()

    val pw by viewModel.paymentWay.collectAsState()
    val value by viewModel.value.collectAsState()
    val description by viewModel.description.collectAsState()
    val date by viewModel.date.collectAsState()

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        Nav.navController?.popBackStack()
                    },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                Text(
                    text = "Compra única",
                    fontSize = 35.sp,
                    modifier = Modifier.padding(10.dp)
                )
                DefaultHelpIconWithTooltip(
                    "",
                    modifier = Modifier.padding(10.dp)
                )
            }
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
                            unselected = "Selecione a categoria",
                            list = categoryList,
                            onItemSelect = {
                                viewModel.setCategory(it as CategoryEntity)
                            }
                        )
                    }
                }
                item {
                    var instantValue by remember {
                        mutableStateOf(value.toString())
                    }
                    TextField(
                        value = "R$ $instantValue",
                        onValueChange = {input ->
                            val formatted = input.replace(Regex("[^0-9,.]"), "")
                            instantValue = formatted

                            val numericValue = formatted.replace(",", ".").toDoubleOrNull() ?: 0.0
                            viewModel.setValue(numericValue)
                        },
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        label = {
                            Text(
                                text = "Valor:",
                                color = Color.Black
                            )
                        },
                        shape = RoundedCornerShape(25.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        singleLine = true,

                        )
                }
                item {
                    TextField(
                        value = description,
                        onValueChange = {
                            viewModel.setDescription(it)
                        },
                        modifier = Modifier.padding(vertical = 5.dp),
                        label = {
                            Text(
                                text = "Descrição:",
                                color = Color.Black
                            )
                        },
                        shape = RoundedCornerShape(25.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        maxLines = 4
                    )
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
                            list = paymentWayList,
                            onItemSelect = {
                                viewModel.setPaymentWay(it as PaymentWayEntity)
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
                                list = personList,
                                unselected = "Selecione...",
                                onItemSelect = {
                                    viewModel.setPerson(it as PersonEntity)
                                }
                            )
                        }
                    }
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(50.dp),
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Button(
                            onClick = { Nav.navController?.popBackStack() },
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun ItemPurchasedUIPrev() {
    ItemPurchasedUI()
}