package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.database.entities.ParcelValueEntity

@Dao
interface ParcelValuesDAO {
    @Insert
    fun inserParcelValue(parcelValue: ParcelValueEntity)

    @Delete
    fun deleteParcelValue(parcelValue: ParcelValueEntity)

    @Query("SELECT * FROM ParcelValueEntity")
    fun selectAllParcelValue(): kotlinx.coroutines.flow.Flow<List<ParcelValueEntity>>
}