package com.rogue.financesrogue.ui.screen.mainUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.ui.screen.mainUI.componentes.CategoriesListResume
import com.rogue.financesrogue.ui.screen.mainUI.componentes.FinancesResume
import com.rogue.financesrogue.ui.screen.mainUI.componentes.MainBottomBar
import com.rogue.financesrogue.ui.screen.mainUI.componentes.MainFloatButton
import com.rogue.financesrogue.ui.screen.mainUI.componentes.PersonListResume
import com.rogue.financesrogue.ui.screen.mainUI.componentes.ProfileHeader

//V2 - 15/01/25
@Composable
fun MainUI() {
    Scaffold(
        containerColor = Color.Gray,
        bottomBar = {
            MainBottomBar()
        },
        floatingActionButton = { MainFloatButton() }
    ) { paddingValues ->
        Column {
            ProfileHeader()
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
            ) {
                item { FinancesResume() }
                item { PersonListResume() }
                item { CategoriesListResume() }
            }
        }
    }
}

@Preview
@Composable
private fun MainPrev() {
    MainUI()
}