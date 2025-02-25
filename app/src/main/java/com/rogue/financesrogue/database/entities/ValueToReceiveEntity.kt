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
        )
    ]
)
data class ValueToReceiveEntity(
    @PrimaryKey(autoGenerate = true)
    val valueToReceiveId: Int? = null,
    val personToReceive: Int,
    val totalPrice: Double,
    val type: Int,
    val parcels: Int = 1,
    val description: String
)
