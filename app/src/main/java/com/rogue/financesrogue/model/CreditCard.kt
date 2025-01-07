package com.rogue.financesrogue.model

data class CreditCard(
    val creditCardId: Int,
    val brand: Brand,
    val name: String,
    val closingDay: Int
)
