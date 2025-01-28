package com.rogue.financesrogue.ui.screen.mainUI

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.ui.screen.mainUI.componentes.CategoriesListResume
import com.rogue.financesrogue.ui.screen.mainUI.componentes.FinancesResume
import com.rogue.financesrogue.ui.screen.mainUI.componentes.PersonListResume

@Composable
fun MainPageUi() {
    LazyColumn(
    ) {
        item { FinancesResume() }
        item { PersonListResume() }
        item { CategoriesListResume() }
    }
}

@Preview
@Composable
private fun MainPageUIPrev() {
    MainPageUi()
}