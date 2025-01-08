package com.rogue.financesrogue.model

data class ItemPurchased(
    val itemPurchasedId: Int,
    val dayOfPurchased: String,
    val category: Category,
    val price: Double,
    val paymentWay: PaymentWay,
    val description: String,
    val payForPerson: Boolean = false,
    val person: Person? = null
)
