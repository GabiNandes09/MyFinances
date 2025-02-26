package com.rogue.financesrogue.ui.screen.allListUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.ui.screen.allListUI.components.ItemAllList
import com.rogue.financesrogue.ui.screen.allListUI.components.SearchAndFilterBar
import com.rogue.financesrogue.ui.screen.allListUI.components.TotalRow
import com.rogue.financesrogue.viewmodel.AllListViewModel
import org.koin.androidx.compose.koinViewModel

//v1 - 27/01/2025
@Composable
fun AllListUI() {
    val viewModel: AllListViewModel = koinViewModel()
    val itemPurchasedList by viewModel.itemPurchasedList.collectAsState()
    val valueToReceiveList by viewModel.valueToReceiveList.collectAsState()
    val fixedValueList by viewModel.fixedValueList.collectAsState()

    Scaffold(
        containerColor = Color.Gray
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TotalRow()
            SearchAndFilterBar()
            LazyColumn {
                itemPurchasedList.forEach {item ->
                    item {
                        ItemAllList(item)
                    }
                }
                valueToReceiveList.forEach { item ->
                    item{
                        ItemAllList(item = item)
                    }
                }
                fixedValueList.forEach { item ->
                    item{
                        ItemAllList(item = item)
                    }
                }
            }
        }
    }
}




@Preview
@Composable
private fun AllListUIPrev() {
    AllListUI()
}