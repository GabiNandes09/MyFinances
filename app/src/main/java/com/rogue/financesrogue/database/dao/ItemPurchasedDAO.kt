package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity

@Dao
interface ItemPurchasedDAO {
    @Insert
    fun insertItemPurchased(itemPurchased: ItemPurchasedEntity)

    @Delete
    fun deleteItemPurchased(itemPurchased: ItemPurchasedEntity)

    @Query("SELECT * FROM ItemPurchasedEntity")
    fun selectAllItemPurchased(): List<ItemPurchasedEntity>
}