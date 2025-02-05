package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.database.entities.MonthValuesEntity

@Dao
interface MonthValuesDAO {
    @Insert
    fun insertMonthValue(monthValue: MonthValuesEntity)

    @Delete
    fun deleteMonthValue(monthValue: MonthValuesEntity)

    @Query("SELECT * FROM MonthValuesEntity")
    fun selectAllMonthValue(): kotlinx.coroutines.flow.Flow<List<MonthValuesEntity>>
}