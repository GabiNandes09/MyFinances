package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.ValueToReceiveEntity

@Dao
interface ValueToReceiveDAO {
    @Insert
    fun insertValueToReceive(valueToReceive: ValueToReceiveEntity)

    @Delete
    fun deleteValueToReceive(valueToReceive: ValueToReceiveEntity)

    @Query("SELECT * FROM ValueToReceiveEntity")
    fun selectAllValueToRecceive(): List<ValueToReceiveEntity>
}