package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.BrandEntity

@Dao
interface BrandDAO {
    @Insert
    fun insertBrand(brand: BrandEntity)

    @Delete
    fun deleteBrand(brand: BrandEntity)

    @Query("SELECT * FROM BrandEntity")
    fun selectAllBrand(): List<BrandEntity>
}