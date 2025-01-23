package com.rogue.financesrogue.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AllListUI() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Composable
fun ItemAllList() {
    
}

@Preview
@Composable
private fun ItemPrev() {
    ItemAllList()
}

@Preview
@Composable
private fun AllListUIPrev() {
    AllListUI()
}