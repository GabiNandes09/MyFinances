package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["personId"],
            childColumns = ["personToReceive"]
        ),
        ForeignKey(
            entity = PaymentWayEntity::class,
            parentColumns = ["paymentWayId"],
            childColumns = ["idPaymentWay"]
        )
    ]
)
data class ValueToReceiveEntity(
    @PrimaryKey(autoGenerate = true)
    val valueToReceiveId: Int,
    val personToReceive: Int,
    val totalPrice: Double,
    val idPaymentWay: Int,
    val type: Int,
    val parcels: Int = 1,
    val description: String
)
