package com.rogue.financesrogue.modules

import com.rogue.financesrogue.database.MyFinancesDatabase
import com.rogue.financesrogue.viewmodel.AllListViewModel
import com.rogue.financesrogue.viewmodel.FixedValueViewModel
import com.rogue.financesrogue.viewmodel.ItemPurchasedViewModel
import com.rogue.financesrogue.viewmodel.MainPageViewModel
import com.rogue.financesrogue.viewmodel.ValueToReceiveViewModel
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
    viewModel {
        AllListViewModel(
            itemRepository = get(),
            valuesToReceiveRepository = get()
        )
    }
    viewModel {
        ValueToReceiveViewModel(
            personRepository = get(),
            valueToReceiveRepository = get()
        )
    }
    viewModel {
        FixedValueViewModel(
            fixedValueRepository = get(),
            categoryRepository = get(),
            paymentWayRepository = get(),
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
    factory { get<MyFinancesDatabase>().ValueToReceiveDAO() }
    factory { get<MyFinancesDatabase>().FixedValueDAO() }
}