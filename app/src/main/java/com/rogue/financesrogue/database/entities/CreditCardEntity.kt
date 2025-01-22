package com.rogue.financesrogue.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.rogue.financesrogue.model.Brand

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = BrandEntity::class,
            parentColumns = ["brandId"],
            childColumns = ["idBrand"]
        )
    ]
)
data class CreditCardEntity(
    @PrimaryKey(autoGenerate = true)
    val creditCardId: Int,
    val idBrand: Int,
    val name: String,
    val closingDay: Int
)