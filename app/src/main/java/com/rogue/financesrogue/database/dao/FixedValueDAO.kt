package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.FixedValueEntity

@Dao
interface FixedValueDAO {
    @Insert
    fun insertFixedValue(fixedValue: FixedValueEntity)

    @Delete
    fun deleteFixedValue(fixedValue: FixedValueEntity)

    @Query("SELECT * FROM FixedValueEntity")
    fun selectAllFixedValue(): List<FixedValueEntity>
}