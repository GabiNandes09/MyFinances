package com.rogue.financesrogue.model

data class ValueToReceive(
    val valueToReceiveId: Int,
    val personToReceive: Person,
    val totalPrice: Double,
    val type: ValueType,
    val parcels: Int = 1,
    val description: String
)
enum class ValueType(val type: Int) {
    UNIQUE(0),
    FIXED(1),
    PARCEL(2)
}
