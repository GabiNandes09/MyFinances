package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaymentWayEntity(
    @PrimaryKey(autoGenerate = true)
    val paymentWayId: Int,
    val paymentWay: String
)