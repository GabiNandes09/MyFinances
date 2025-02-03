package com.rogue.financesrogue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogue.financesrogue.database.entities.PaymentWayEntity

@Dao
interface PaymentWayDAO {
    @Insert
    suspend fun insertPaymentWay(paymentWay: PaymentWayEntity)

    @Delete
    suspend fun deletePaymentWay(paymentWay: PaymentWayEntity)

    @Query("SELECT * FROM PaymentWayEntity")
    suspend fun selectAllPaymentWay(): List<PaymentWayEntity>
}