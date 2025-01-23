package com.rogue.financesrogue.modules

import androidx.room.Room
import com.rogue.financesrogue.database.MyFinancesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

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