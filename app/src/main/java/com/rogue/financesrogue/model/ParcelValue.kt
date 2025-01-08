package com.rogue.financesrogue.model

data class ParcelValue(
    val parcelValueId: Int,
    val totalValue: Double,
    val category: Category,
    val parcels: Int,
    val description: String,
    val payForPerson: Boolean,
    val person: Person?
)
