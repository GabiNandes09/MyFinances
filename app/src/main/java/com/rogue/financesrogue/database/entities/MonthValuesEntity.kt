package com.rogue.financesrogue.database.entities

import androidx.room.Entity

@Entity(
    primaryKeys = ["month", "year"]
)
data class MonthValuesEntity(
    val month: Int,
    val year: Int,
    val totalToPay: Double = 0.0,
    val totalParcel: Double = 0.0
)
