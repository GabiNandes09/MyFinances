package com.rogue.financesrogue.modules

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rogue.financesrogue.database.MyFinancesDatabase
import com.rogue.financesrogue.viewmodel.ItemPurchasedViewModel
import com.rogue.financesrogue.viewmodel.MainPageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainPageViewModel()
    }
    viewModel{
        ItemPurchasedViewModel(
            itemRepository = get(),
            paymentWayRepository = get(),
            categoryRepository = get(),
            personRepository = get()
        )
    }
}

val storageModule = module {
    single {
        MyFinancesDatabase.getDatabase(androidContext())
    }
    factory {get<MyFinancesDatabase>().PaymentWayDAO()}
    factory {get<MyFinancesDatabase>().ItemPurchasedDAO()}
    factory {get<MyFinancesDatabase>().CategoryDAO()}
    factory {get<MyFinancesDatabase>().PersonDAO()}
}