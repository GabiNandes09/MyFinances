package com.rogue.financesrogue

import android.app.Application
import com.rogue.financesrogue.database.MyFinancesDatabase
import com.rogue.financesrogue.modules.appModule
import com.rogue.financesrogue.modules.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, storageModule)
        }

        getDatabaseInstance()
    }

    private fun getDatabaseInstance() {
        val db = MyFinancesDatabase.getDatabase(applicationContext)
        db.openHelper.writableDatabase
    }
}