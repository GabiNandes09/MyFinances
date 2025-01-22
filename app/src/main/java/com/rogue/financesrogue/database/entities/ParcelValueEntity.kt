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
            entity = PersonEntity::class,
            parentColumns = ["personId"],
            childColumns = ["idPerson"]
        )
    ]
)
data class ParcelValueEntity(
    @PrimaryKey(autoGenerate = true)
    val parcelValueId: Int,
    val totalValue: Double,
    val idCategory: Int,
    val parcels: Int,
    val description: String,
    val payForPerson: Boolean,
    val idPerson: Int?
)