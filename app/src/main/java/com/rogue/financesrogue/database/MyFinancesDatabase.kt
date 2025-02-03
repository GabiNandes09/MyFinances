package com.rogue.financesrogue.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rogue.financesrogue.database.dao.BrandDAO
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.CreditCardDAO
import com.rogue.financesrogue.database.dao.FixedValueDAO
import com.rogue.financesrogue.database.dao.ItemPurchasedDAO
import com.rogue.financesrogue.database.dao.MonthValuesDAO
import com.rogue.financesrogue.database.dao.ParcelValuesDAO
import com.rogue.financesrogue.database.dao.PaymentWayDAO
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.dao.UserDAO
import com.rogue.financesrogue.database.dao.ValueToReceiveDAO
import com.rogue.financesrogue.database.entities.BrandEntity
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.CreditCardEntity
import com.rogue.financesrogue.database.entities.FixedValueEntity
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.database.entities.MonthValuesEntity
import com.rogue.financesrogue.database.entities.ParcelValueEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.database.entities.UserEntity
import com.rogue.financesrogue.database.entities.ValueToReceiveEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        BrandEntity::class,
        CategoryEntity::class,
        CreditCardEntity::class,
        FixedValueEntity::class,
        ItemPurchasedEntity::class,
        MonthValuesEntity::class,
        ParcelValueEntity::class,
        PaymentWayEntity::class,
        PersonEntity::class,
        UserEntity::class,
        ValueToReceiveEntity::class
    ],
    version = 1
)
abstract class MyFinancesDatabase : RoomDatabase() {
    abstract fun BrandDAO(): BrandDAO
    abstract fun CategoryDAO(): CategoryDAO
    abstract fun CreditCardDAO(): CreditCardDAO
    abstract fun FixedValueDAO(): FixedValueDAO
    abstract fun ItemPurchasedDAO(): ItemPurchasedDAO
    abstract fun MonthValuesDAO(): MonthValuesDAO
    abstract fun ParcelValuesDAO(): ParcelValuesDAO
    abstract fun PaymentWayDAO(): PaymentWayDAO
    abstract fun PersonDAO(): PersonDAO
    abstract fun userDao(): UserDAO
    abstract fun ValueToReceiveDAO(): ValueToReceiveDAO

    companion object {
        @Volatile
        private var INSTANCE: MyFinancesDatabase? = null

        fun getDatabase(context: Context): MyFinancesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyFinancesDatabase::class.java,
                    "MyFinances.db"
                )
                    .addCallback(DatabaseCallback()) // Adicionando o callback
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(db: MyFinancesDatabase) {
            val paymentWayDefault = listOf("PIX", "CARTÃO DE CRÉDITO", "BOLETO", "CARTÃO DE DÉBITO", "PESSOA", "DINHEIRO")
            val categoryDefault = listOf("MERCADO", "COMBUSTÍVEL", "CASA", "HOBBY", "ROLÊ")

            paymentWayDefault.forEach{pw ->
                db.PaymentWayDAO().insertPaymentWay(
                    PaymentWayEntity(
                        paymentWay = pw
                    )
                )
            }

            categoryDefault.forEach { cat ->
                db.CategoryDAO().insertCategory(
                    CategoryEntity(
                        category = cat
                    )
                )
            }
        }
    }
}

