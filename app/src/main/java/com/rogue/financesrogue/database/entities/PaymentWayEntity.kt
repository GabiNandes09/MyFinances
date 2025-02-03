package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaymentWayEntity(
    @PrimaryKey(autoGenerate = true)
    val paymentWayId: Int? = null,
    val paymentWay: String
){
    override fun toString(): String {
        return paymentWay
    }
}

