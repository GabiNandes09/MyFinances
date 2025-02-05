package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.CategoryEntity
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity

@Dao
interface CategoryDAO {
    @Insert
    suspend fun insertCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM CategoryEntity")
    fun selectAllCategory(): kotlinx.coroutines.flow.Flow<List<CategoryEntity>>

    @Query("SELECT * FROM CategoryEntity WHERE categoryId = :id")
    suspend fun selectOneCategory(id: Int): CategoryEntity
}