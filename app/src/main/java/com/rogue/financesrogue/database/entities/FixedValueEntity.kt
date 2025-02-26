package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["categoryId"],
            childColumns = ["idCategory"]
        ),
        ForeignKey(
            entity = PaymentWayEntity::class,
            parentColumns = ["paymentWayId"],
            childColumns = ["idPaymentWay"]
        ),
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["personId"],
            childColumns = ["idPerson"]
        )
    ]
)
data class FixedValueEntity(
    @PrimaryKey(autoGenerate = true)
    val fixedValuesId: Int? = null,
    val idCategory: Int,
    val price: Double,
    val idPaymentWay: Int,
    val payForPerson: Boolean,
    val variable: Boolean,
    val description: String,
    val idPerson: Int?
)