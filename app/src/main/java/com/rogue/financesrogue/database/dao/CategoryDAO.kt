package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.CategoryEntity

@Dao
interface CategoryDAO {
    @Insert
    fun insertCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM CategoryEntity")
    fun selectAllCategory(): List<CategoryEntity>
}