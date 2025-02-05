package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.database.entities.UserEntity

@Dao
interface UserDAO {
    @Insert
    fun insertUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity;")
    fun selectAllUser(): kotlinx.coroutines.flow.Flow<List<UserEntity>>
}