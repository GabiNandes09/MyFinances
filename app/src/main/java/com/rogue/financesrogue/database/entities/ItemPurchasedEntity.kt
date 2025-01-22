package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.rogue.financesrogue.model.Category
import com.rogue.financesrogue.model.PaymentWay
import com.rogue.financesrogue.model.Person

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
data class ItemPurchasedEntity(
    @PrimaryKey(autoGenerate = true)
    val itemPurchasedId: Int,
    val dayOfPurchased: String,
    val idCategory: Int,
    val price: Double,
    val idPaymentWay: Int,
    val description: String,
    val payForPerson: Boolean = false,
    val idPerson: Int? = null
)