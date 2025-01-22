package com.rogue.financesrogue.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rogue.financesrogue.database.dao.UserDAO
import com.rogue.financesrogue.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1
)
abstract class MyFinancesDatabase: RoomDatabase() {

    abstract fun userDao() : UserDAO
}