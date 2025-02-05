package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.CreditCardEntity
import com.rogue.financesrogue.database.entities.ItemPurchasedEntity
import com.rogue.financesrogue.model.CreditCard

@Dao
interface CreditCardDAO {
    @Insert
    fun insertCreditCard(creditCard: CreditCardEntity)

    @Delete
    fun deleteCreditCard(creditCard: CreditCardEntity)

    @Query("SELECT * FROM CreditCardEntity")
    fun selectAllCreditCard(): kotlinx.coroutines.flow.Flow<List<CreditCardEntity>>
}