package com.rogue.financesrogue.ui.screen.mainUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.ui.screen.allListUI.AllListUI
import com.rogue.financesrogue.ui.screen.mainUI.componentes.CategoriesListResume
import com.rogue.financesrogue.ui.screen.mainUI.componentes.FinancesResume
import com.rogue.financesrogue.ui.screen.mainUI.componentes.MainBottomBar
import com.rogue.financesrogue.ui.screen.mainUI.componentes.MainFloatButton
import com.rogue.financesrogue.ui.screen.mainUI.componentes.PersonListResume
import com.rogue.financesrogue.ui.screen.mainUI.componentes.ProfileHeader

//V2 - 15/01/25
@Composable
fun MainUI() {
    var pageState = rememberPagerState {
        2
    }

    Scaffold(
        containerColor = Color.Gray,
        bottomBar = {
            MainBottomBar(pageState.currentPage)
        },
        floatingActionButton = {
            MainFloatButton()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            ProfileHeader()
            HorizontalPager(state = pageState) {page ->
                when(page){
                    0 -> MainPageUi()
                    1 -> AllListUI()
                }
                
            }
            MainPageUi()
        }
    }
}

@Preview
@Composable
private fun MainPrev() {
    MainUI()
}