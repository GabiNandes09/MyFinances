package com.rogue.financesrogue.model

data class MonthValues(
    val month: Int,
    val year: Int,
    val totalToPay: Double = 0.0,
    val totalParcel: Double = 0.0
)
