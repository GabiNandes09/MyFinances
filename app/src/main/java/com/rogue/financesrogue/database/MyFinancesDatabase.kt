package com.rogue.financesrogue.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rogue.financesrogue.database.dao.BrandDAO
import com.rogue.financesrogue.database.dao.UserDAO
import com.rogue.financesrogue.database.entities.BrandEntity
import com.rogue.financesrogue.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        BrandEntity::class
    ],
    version = 1
)
abstract class MyFinancesDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO
    abstract fun BrandDAO(): BrandDAO
}