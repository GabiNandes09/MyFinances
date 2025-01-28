package com.rogue.financesrogue.ui.screen.mainUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rogue.financesrogue.ui.screen.allListUI.AllListUI
import com.rogue.financesrogue.ui.screen.mainUI.componentes.MainBottomBar
import com.rogue.financesrogue.ui.screen.mainUI.componentes.MainFloatButton
import com.rogue.financesrogue.ui.screen.mainUI.componentes.ProfileHeader
import com.rogue.financesrogue.viewmodel.MainPageViewModel
import org.koin.androidx.compose.koinViewModel

//V2 - 15/01/25
@Composable
fun MainUI() {
    val viewModel: MainPageViewModel = koinViewModel()
    val pageState = rememberPagerState { viewModel.bottomBarItens.size }
    val selectedItem by viewModel.selectedItem.collectAsState()

    LaunchedEffect(key1 = selectedItem) {
        pageState.animateScrollToPage(selectedItem)
    }
    LaunchedEffect(key1 = pageState.targetPage) {
        viewModel.chanceSelectedTo(pageState.targetPage)
    }

    Scaffold(
        containerColor = Color.Gray,
        bottomBar = {
            MainBottomBar(
                selectedItem = selectedItem,
                viewModel = viewModel
            )
        },
        floatingActionButton = {
            MainFloatButton()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            ProfileHeader()
            HorizontalPager(
                state = pageState,
                modifier = Modifier.fillMaxSize()
                ) { page ->
                when (page) {
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