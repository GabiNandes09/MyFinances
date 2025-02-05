package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import java.util.concurrent.Flow

@Dao
interface ItemPurchasedDAO {
    @Insert
    suspend fun insertItemPurchased(itemPurchased: ItemPurchasedEntity)

    @Delete
    suspend fun deleteItemPurchased(itemPurchased: ItemPurchasedEntity)

    @Query("SELECT * FROM ItemPurchasedEntity")
    fun selectAllItemPurchased(): kotlinx.coroutines.flow.Flow<List<ItemPurchasedEntity>>
}