package com.rogue.financesrogue.modules

import androidx.room.Room
import com.rogue.financesrogue.database.MyFinancesDatabase
import com.rogue.financesrogue.viewmodel.MainPageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainPageViewModel()
    }
}

val storageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MyFinancesDatabase::class.java,
            "MyFinances.db"
        ).build()
    }
}