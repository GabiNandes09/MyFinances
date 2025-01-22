package com.rogue.financesrogue.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rogue.financesrogue.database.dao.BrandDAO
import com.rogue.financesrogue.database.dao.CategoryDAO
import com.rogue.financesrogue.database.dao.CreditCardDAO
import com.rogue.financesrogue.database.dao.FixedValueDAO
import com.rogue.financesrogue.database.dao.PaymentWayDAO
import com.rogue.financesrogue.database.dao.PersonDAO
import com.rogue.financesrogue.database.dao.UserDAO
import com.rogue.financesrogue.database.entities.BrandEntity
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.CreditCardEntity
import com.rogue.financesrogue.database.entities.FixedValueEntity
import com.rogue.financesrogue.database.entities.PaymentWayEntity
import com.rogue.financesrogue.database.entities.PersonEntity
import com.rogue.financesrogue.database.entities.UserEntity

@Database(
    entities = [
        BrandEntity::class,
        CategoryEntity::class,
        CreditCardEntity::class,
        FixedValueEntity::class,
        PaymentWayEntity::class,
        PersonEntity::class,
        UserEntity::class,
    ],
    version = 1
)
abstract class MyFinancesDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO
    abstract fun BrandDAO(): BrandDAO
    abstract fun CategoryDAO(): CategoryDAO
    abstract fun CreditCardDAO(): CreditCardDAO
    abstract fun FixedValueDAO(): FixedValueDAO
    abstract fun PaymentWayDAO(): PaymentWayDAO
    abstract fun PersonDAO(): PersonDAO
}