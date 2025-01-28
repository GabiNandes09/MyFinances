package com.rogue.financesrogue.ui.screen.allListUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.ui.screen.allListUI.components.ItemAllList
import com.rogue.financesrogue.ui.screen.allListUI.components.SearchAndFilterBar
import com.rogue.financesrogue.ui.screen.allListUI.components.TotalRow
import com.rogue.financesrogue.ui.screen.mainUI.componentes.ProfileHeader

//v1 - 27/01/2025
@Composable
fun AllListUI() {
    Scaffold(
        containerColor = Color.Gray
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TotalRow()
            SearchAndFilterBar()
            LazyColumn {
                for (i in 1..5) {
                    item {
                        ItemAllList()
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