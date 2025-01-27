package com.rogue.financesrogue.ui.screen.allListUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogue.financesrogue.R
import com.rogue.financesrogue.ui.screen.allListUI.components.ItemAllList
import com.rogue.financesrogue.ui.screen.allListUI.components.SearchAndFilterBar
import com.rogue.financesrogue.ui.screen.allListUI.components.TotalRow
import com.rogue.financesrogue.ui.screen.mainUI.componentes.ProfileHeader

@Composable
fun AllListUI() {
    Scaffold(
        containerColor = Color.Gray
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            ProfileHeader()
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