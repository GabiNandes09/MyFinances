package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.BrandEntity
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity

@Dao
interface BrandDAO {
    @Insert
    fun insertBrand(brand: BrandEntity)

    @Delete
    fun deleteBrand(brand: BrandEntity)

    @Query("SELECT * FROM BrandEntity")
    fun selectAllBrand(): kotlinx.coroutines.flow.Flow<List<BrandEntity>>
}