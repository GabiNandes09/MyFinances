package com.rogue.financesrogue.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.ui.screen.main.componentes.CategoriesListResume
import com.rogue.financesrogue.ui.screen.main.componentes.ProfileHeader

//V1 - 09/01/25
@Composable
fun Main() {
    Scaffold(
        containerColor = Color.Gray
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            ProfileHeader()
            CategoriesListResume()
        }
    }
}

@Preview
@Composable
private fun MainPrev() {
    Main()
}